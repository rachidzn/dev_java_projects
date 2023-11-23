export class Article{
    public constructor(public id :number,
                        public nom: string,
                        public marque: string
                         )
                         
                       
                         {}
                         public copyFrom(other: Article){
                            this.id=other.id;
                            this.nom=other.nom;
                            this.marque=other.marque;
                            
                           
                    
                        }
                    
                        public toJson(){
                            return {
                                id:this.id,
                                nom:this.nom,
                                marque: this.marque,
                                
                                
                            };
                        }
    }
    
