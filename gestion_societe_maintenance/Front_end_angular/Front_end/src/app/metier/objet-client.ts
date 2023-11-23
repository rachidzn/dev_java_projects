export class Client{
    public constructor(public id :number,
                        public nom: string,
                        public email: string,
                         public nbdesite: number,
                        
                        
                        )
                         
                       
                         {}
    

    public copyFrom(other: Client){
        this.id=other.id;
        this.nom=other.nom;
        this.email=other.email;
        this.nbdesite=other.nbdesite;
       

    }

    public toJson(){
        return {
            id:this.id,
            nom:this.nom,
            email: this.email,
            nbdesite: this.nbdesite,
            
        };
    }
}
