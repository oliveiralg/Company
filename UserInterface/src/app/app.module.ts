import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClient, HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';

// Import the Animations module
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Import the ButtonsModule
import { ButtonsModule } from '@progress/kendo-angular-buttons';

// Import the Grid
import { GridModule } from '@progress/kendo-angular-grid';
import { CampanhaService } from './service/campanha.service';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        HttpClientModule,
        HttpClientJsonpModule,
        BrowserModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        FormsModule,
        HttpModule,

        // Register the modules
        BrowserAnimationsModule,
        ButtonsModule,
        GridModule
    ],
    providers: [CampanhaService],
    bootstrap: [AppComponent]
})
export class AppModule { }
