import { Component, OnInit } from '@angular/core';
import { Client } from '../../metier/objet-client';
import { ClientRepositoryService } from '../../services/client-repository.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  public Clientconnected: Client=new Client(0,"","",0);


  constructor( private clientRepositoryService: ClientRepositoryService) { }

  ngOnInit() {
    this.clientRepositoryService.findById(
      this.clientRepositoryService.setclient_toconnect()).subscribe(Client=> { this.Clientconnected=Client;
    },);
    console.log(this.Clientconnected);
  }

}
