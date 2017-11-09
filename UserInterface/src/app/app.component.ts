import { Observable } from 'rxjs/Observable';
import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { GridDataResult } from '@progress/kendo-angular-grid';
import { State, process } from '@progress/kendo-data-query';

import { CampanhaService } from './service/campanha.service';
import { Campanha } from './model/campanha';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [CampanhaService],
})
export class AppComponent implements OnInit {
    public view: Observable<GridDataResult>;
    public gridState: State = {
        sort: [],
        skip: 0,
        take: 10
    };
    public formGroup: FormGroup;

    private campanhaService: CampanhaService;
    private editedRowIndex: number;

    constructor(@Inject(CampanhaService) campanhaServiceFactory: any) {
        this.campanhaService = campanhaServiceFactory;
    }

    public ngOnInit(): void {
        this.view = this.campanhaService.map(data => process(data, this.gridState));
        this.campanhaService.read();
    }

    public onStateChange(state: State) {
        this.gridState = state;
        this.campanhaService.read();
    }

    protected addHandler({sender}) {
        this.closeEditor(sender);

        this.formGroup = new FormGroup({
            'id': new FormControl(),
            'nome': new FormControl('', Validators.required),
            'timeCoracao': new FormControl('', Validators.compose([Validators.required, Validators.pattern('^[0-9]{1,3}')])),
            'vigenciaInicio': new FormControl(),
            'vigenciaFim': new FormControl()
        });

        sender.addRow(this.formGroup);
    }

    protected editHandler({sender, rowIndex, dataItem}) {
        this.closeEditor(sender);

        this.formGroup = new FormGroup({
            'id': new FormControl(),
            'nome': new FormControl('', Validators.required),
            'timeCoracao': new FormControl('', Validators.compose([Validators.required, Validators.pattern('^[0-9]{1,3}')])),
            'vigenciaInicio': new FormControl(),
            'vigenciaFim': new FormControl()
        });

        this.editedRowIndex = rowIndex;

        sender.editRow(rowIndex, this.formGroup);
    }

    protected cancelHandler({sender, rowIndex}) {
        this.closeEditor(sender, rowIndex);
    }

    private closeEditor(grid, rowIndex = this.editedRowIndex) {
        grid.closeRow(rowIndex);
        this.editedRowIndex = undefined;
        this.formGroup = undefined;
    }

    protected saveHandler({sender, rowIndex, formGroup, isNew}) {
        const campanha: Campanha = formGroup.value;

        this.campanhaService.update(campanha, isNew);

        sender.closeRow(rowIndex);
    }

    protected removeHandler({dataItem}) {
        this.campanhaService.delete(dataItem);
    }
}
