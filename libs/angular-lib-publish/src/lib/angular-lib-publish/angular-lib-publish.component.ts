import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
    selector: 'lib-angular-lib-publish',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './angular-lib-publish.component.html',
    styleUrl: './angular-lib-publish.component.css',
})
export class AngularLibThreeComponent {
    name = 'comp';
}
