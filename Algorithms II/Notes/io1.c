#include <stdio.h>

struct rec_t {
    int ID ;                       // Assume 1 <= ID <= 100
    char Name[50] ;
    double Score ;
};
int main( ) {
    FILE * cfPtr ;
    struct rec_t Rec ;

    cfPtr = fopen( "Score.dat", "w" ) ;
    while( scanf( "%d", &Rec.ID) != EOF ) {
        scanf( "%s%lf", Rec.Name, &Rec.Score ) ;

        fseek( cfPtr, (Rec.ID – 1) * sizeof( struct rec_t ), SEEK_SET ) ;     //Note that an uninitilized record can still be accessed, but garbage data will be found
        fwrite( &Rec, sizeof( struct rec_t ), 1, cfPtr ) ;
    } 

    fclose( cfPtr ) ;   //Kent's pet peeve number 2; ALWAYS CLOSE FOR GOOD MANNERS
    return 0 ;
}
