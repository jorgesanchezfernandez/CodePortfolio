using System.Collections.Generic;
using System.Linq;
using PholioVisualisation.Analysis;
using PholioVisualisation.DataAccess;
using PholioVisualisation.DataConstruction;
using PholioVisualisation.Export.FileBuilder.Writers;
using PholioVisualisation.Formatting;
using PholioVisualisation.PholioObjects;

namespace PholioVisualisation.Export.FileBuilder.Containers
{
    public class IndicatorDataBodyContainer
    {
        private readonly IndicatorMetadataProvider _indicatorMetadataProvider;
        private readonly ExportAreaHelper _areaHelper;
        private readonly IAreasReader _areasReader;
        private readonly PholioReader _pholioReader = ReaderFactory.GetPholioReader();
        private readonly CsvBuilderAttributesForBodyContainer _attributesForBodyContainer;

        private CsvBuilderIndicatorDataBodyPeriodWriter _bodyPeriodWriter;
        private LookUpManager _lookUpManager;
        private SingleIndicatorFileWriter fileWriter;

        public IndicatorDataBodyContainer(IndicatorMetadataProvider indicatorMetadataProvider, ExportAreaHelper areaHelper, 
            IAreasReader areasReader, CsvBuilderAttributesForBodyContainer attributesForBodyContainer)
        {
            _indicatorMetadataProvider = indicatorMetadataProvider;
            _areaHelper = areaHelper;
            _areasReader = areasReader;
            _attributesForBodyContainer = attributesForBodyContainer;
        }

        public IndicatorComparer AssistanceComparer { get; private set; }

        public byte[] GetIndicatorDataFile(int indicatorId)
        {
            fileWriter = new SingleIndicatorFileWriter(indicatorId, _attributesForBodyContainer.GeneralParameters);

            // Don't recalculate if exist
            if (HasBeenWrittenFileFromCache(fileWriter))
            {
                return getWrittenFileFromCache(fileWriter);
            }

            if (HasBeenIgnoreFileWriterSettings(indicatorId))
            {
                // Empty file
                return new byte[] { };
            }
            
            var grouping = _attributesForBodyContainer.GetGrouping(indicatorId, _attributesForBodyContainer.OnDemandParameters.GroupIds);
            var indicatorMetadata = FindIndicatorMetadata(grouping);

            WriterInitialization(grouping, indicatorMetadata);
            WriteIndicatorDataBodyInFile(indicatorId, indicatorMetadata, grouping);

            return fileWriter.GetFileContent();
        }

        private void WriteIndicatorDataBodyInFile(int indicatorId, IndicatorMetadata indicatorMetadata, Grouping grouping)
        {
            _bodyPeriodWriter = new CsvBuilderIndicatorDataBodyPeriodWriter(indicatorMetadata, grouping, _areaHelper, 
                _attributesForBodyContainer.GroupDataReader,  _attributesForBodyContainer.GeneralParameters, 
                _attributesForBodyContainer.OnDemandParameters, AssistanceComparer);

            _bodyPeriodWriter.WritePeriodsForIndicatorBodyInFile(indicatorId, fileWriter);
        }

        private static bool HasBeenWrittenFileFromCache(SingleIndicatorFileWriter fileWriter)
        {

            byte[] bytes = null;
            bytes = fileWriter.TryLoadFile();
            return bytes != null;
        }

        private static byte[] getWrittenFileFromCache(SingleIndicatorFileWriter fileWriter)
        {
            return fileWriter.TryLoadFile();
        }

        private bool HasBeenIgnoreFileWriterSettings(int indicatorId)
        {
            var grouping = _attributesForBodyContainer.GetGrouping(indicatorId, _attributesForBodyContainer.OnDemandParameters.GroupIds);

            if (grouping != null)
            {
                var indicatorMetadata = FindIndicatorMetadata(grouping);
                if (indicatorMetadata != null)
                {
                    return false;
                }
            }

            return true;
        }

        public void WriterInitialization(Grouping grouping, IndicatorMetadata indicatorMetadata){
            // Lazy initialisation (would not be necessary if cached files are available)
            _areaHelper.Init();

            InitFileWriter(grouping, indicatorMetadata);
            InitComparer(grouping);
        }

        private void InitFileWriter(Grouping grouping, IndicatorMetadata indicatorMetadata)
        {
            var trendMarkerLabelProvider = new TrendMarkerLabelProvider(grouping.PolarityId);
            var significanceFormatter = new SignificanceFormatter(grouping.PolarityId, grouping.ComparatorMethodId);
            fileWriter.Init(LookUpManager, trendMarkerLabelProvider, significanceFormatter, indicatorMetadata);
        }

        private void InitComparer(Grouping grouping)
        {
            AssistanceComparer = null;
            if (grouping.IsComparatorDefined())
            {
                AssistanceComparer = new IndicatorComparerFactory { PholioReader = _pholioReader }.New(grouping);
            }
        }

        private IndicatorMetadata FindIndicatorMetadata(Grouping grouping)
        {
            return _indicatorMetadataProvider.GetIndicatorMetadata(new List<Grouping> { grouping }, _attributesForBodyContainer.AttributesForIndicators.IndicatorMetadataTextOption).First();
        }

        /// <summary>
        /// Lazily initialise look up manager. May not need these if all files are already cached.
        /// </summary>
        private LookUpManager LookUpManager
        {
            get
            {
                if (_lookUpManager == null)
                {
                    // Init look up manager
                    var allAreaTypes = new List<int>
                    {
                        AreaTypeIds.Country,
                        _attributesForBodyContainer.GeneralParameters.ParentAreaTypeId,
                        _attributesForBodyContainer.GeneralParameters.ChildAreaTypeId
                    };
                    string publicId = null;
                    if (Area.IsAreaListAreaCode(_attributesForBodyContainer.GeneralParameters.ParentAreaCode))
                    {
                        publicId = _attributesForBodyContainer.GeneralParameters.ParentAreaCode;
                    }
                    var categoryTypeIds = _areasReader.GetAllCategoryTypes().Select(x => x.Id).ToList();
                    _lookUpManager = new LookUpManager(_pholioReader, _areasReader, allAreaTypes, categoryTypeIds, publicId);
                }
                return _lookUpManager;
            }
        }
    }

}