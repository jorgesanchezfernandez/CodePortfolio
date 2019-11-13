import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CompareAreaComponent } from './compare-area.component';
import { CompareAreaChartComponent } from './compare-area-chart/compare-area-chart.component';
import { CompareAreaTableComponent } from './compare-area-table/compare-area-table.component';
import { LegendComponent } from '../shared/component/legend/legend.component';
import { LegendAreaProfilesComponent } from '../shared/component/legend/legend-area-profiles/legend-area-profiles.component';
import { LegendCompareAreasComponent } from '../shared/component/legend/legend-compare-areas/legend-compare-areas.component';
import { LegendInequalitiesComponent } from '../shared/component/legend/legend-inequalities/legend-inequalities.component';
import { LegendMapComponent } from '../shared/component/legend/legend-map/legend-map.component';
import { LegendRecentTrendsComponent } from '../shared/component/legend/legend-recent-trends/legend-recent-trends.component';
import { LegendTrendComponent } from '../shared/component/legend/legend-trend/legend-trend.component';
import { FTHelperService } from '../shared/service/helper/ftHelper.service';
import { IndicatorService } from '../shared/service/api/indicator.service';
import { UIService } from '../shared/service/helper/ui.service';
import { DownloadService } from '../shared/service/api/download.service';
import { ComparatorIds, AreaTypeIds } from '../shared/shared';

describe('CompareAreaComponent', () => {
  let component: CompareAreaComponent;
  let fixture: ComponentFixture<CompareAreaComponent>;

  let ftHelperService: FTHelperService;
  let uiService: any;
  let downloadService: any;
  let indicatorService: any;

  beforeEach(async(() => {

    ftHelperService = jasmine.createSpyObj('FTHelperService', ['setComparatorId']);
    uiService = jasmine.createSpyObj('UIService', ['']);
    downloadService = jasmine.createSpyObj('DownloadService', ['']);
    indicatorService = jasmine.createSpyObj('IndicatorService', ['']);

    TestBed.configureTestingModule({
      declarations: [
        CompareAreaComponent,
        CompareAreaChartComponent,
        CompareAreaTableComponent,
        LegendComponent,
        LegendAreaProfilesComponent,
        LegendCompareAreasComponent,
        LegendInequalitiesComponent,
        LegendMapComponent,
        LegendRecentTrendsComponent,
        LegendTrendComponent
      ],
      providers: [
        { provide: FTHelperService, useValue: ftHelperService },
        { provide: UIService, useValue: uiService },
        { provide: DownloadService, useValue: downloadService },
        { provide: IndicatorService, useValue: indicatorService }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompareAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return true for areatypes', () => {
    fixture = TestBed.createComponent(CompareAreaComponent);
    component = fixture.componentInstance;
    let gp = AreaTypeIds.Practice;
    let ward = AreaTypeIds.Ward;
    let lsoa = AreaTypeIds.LSOA;
    let msoa = AreaTypeIds.MSOA;

    var result = component.isRestrictedAllInEnglandOpts(gp);
    result = result && component.isRestrictedAllInEnglandOpts(ward);
    result = result && component.isRestrictedAllInEnglandOpts(lsoa);
    result = result && component.isRestrictedAllInEnglandOpts(msoa);

    expect(result).toBeTruthy();
  })

  it('should set values for restricted areatypes', () => {
    fixture = TestBed.createComponent(CompareAreaComponent);
    component = fixture.componentInstance;
    let gp = AreaTypeIds.Practice;
    let ward = AreaTypeIds.Ward;
    let lsoa = AreaTypeIds.LSOA;
    let msoa = AreaTypeIds.MSOA;

    spyOn(component, 'isRestrictedAllInEnglandOpts');

    component.setValuesForRestrictedAreaTypes(gp);
    expect(ftHelperService.setComparatorId).toHaveBeenCalled();

    component.setValuesForRestrictedAreaTypes(ward);
    expect(ftHelperService.setComparatorId).toHaveBeenCalled();

    component.setValuesForRestrictedAreaTypes(lsoa);
    expect(ftHelperService.setComparatorId).toHaveBeenCalled();

    component.setValuesForRestrictedAreaTypes(msoa);
    expect(ftHelperService.setComparatorId).toHaveBeenCalled();
  })
});