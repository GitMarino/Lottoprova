import { allIcons } from 'angular-feather/icons';
import { FeatherModule } from 'angular-feather';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    FeatherModule.pick(allIcons)
  ],
  exports: [FeatherModule]
})
export class IconsModule {
}