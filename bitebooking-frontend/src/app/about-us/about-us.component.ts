import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgbAccordionModule } from '@ng-bootstrap/ng-bootstrap';
import Aos from 'aos';


@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [NgbAccordionModule],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.css'
})
export class AboutUsComponent implements OnInit {

  @ViewChild('skills') skillsElement!: ElementRef;

  constructor() { }

  ngOnInit(): void {
    Aos.init();
    this.animateProgressBars();

    window.scrollTo(0, 0); 
  }

  animateProgressBars() {
    const skillsElement = this.skillsElement.nativeElement;
    const bars = skillsElement.querySelectorAll('.progress-bar');

    bars.forEach((bar: any) => {
      const percentage = parseInt(bar.getAttribute('aria-valuenow'));
      let currentWidth = 0;

      const interval = setInterval(() => {
        if (currentWidth >= percentage) {
          clearInterval(interval);
        } else {
          currentWidth++;
          bar.style.width = currentWidth + '%';
        }
      }, 10);
    });
  }

}