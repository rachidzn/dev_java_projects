import { Component , Inject, ViewChild, ElementRef } from '@angular/core';
import { PageScrollConfig, PageScrollService, PageScrollInstance } from 'ngx-page-scroll';
import { DOCUMENT} from '@angular/common';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';


  //Page-scroll imports
  @ViewChild('container')
  private container: ElementRef;

  constructor(private pageScrollService: PageScrollService, @Inject(DOCUMENT) private document: any) {
    PageScrollConfig.defaultDuration = 600;



  }




  



    /*    Besoin pour ng page scroll   */
  public goToHead2(): void {
      let pageScrollInstance: PageScrollInstance = PageScrollInstance.simpleInstance(this.document, '#head2');
      this.pageScrollService.start(pageScrollInstance);
  };    
  
  public scrollSomewhereHorizontally(): void {
      let pageScrollInstance: PageScrollInstance = PageScrollInstance.newInstance({document: this.document, scrollTarget: '#targetToTheRight', verticalScrolling: false});
      this.pageScrollService.start(pageScrollInstance);
  }; 

  public goToHeadingInContainer(): void {
      let pageScrollInstance: PageScrollInstance = PageScrollInstance.newInstance({document: this.document, scrollTarget: '.headingClass', scrollingViews: [this.container.nativeElement]});
      this.pageScrollService.start(pageScrollInstance);
  };
}
