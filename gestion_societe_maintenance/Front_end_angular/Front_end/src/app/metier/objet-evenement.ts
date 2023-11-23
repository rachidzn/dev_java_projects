export class Evenement{
    public constructor(
                        public title: string,
                        public start: string,
                        public end:string
                         )
                         
                       
                         {}
    

    public copyFrom(other: Evenement){

        this.title=other.title;
        this.start=other.start;
        this.end=other.end;
       

    }

    public toJson(){
        return {

            title:this.title,
            start: this.start,
            end: this.end,
            
        };
    }
}
