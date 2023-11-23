export class Site{
    public constructor(public id :number,
                        public nom: string,
                        public adresse: string,
                         public id_Client: number)
                         
                       
                         {}
    

    public copyFrom(other: Site){
        this.id=other.id;
        this.nom=other.nom;
        this.adresse=other.adresse;
        this.id_Client=other.id_Client;
     
    }
    public toJson(){
        return {
            id:this.id,
            nom:this.nom,
            adresse:this.adresse,
            id_Client:this.id_Client
        }
    }
}
