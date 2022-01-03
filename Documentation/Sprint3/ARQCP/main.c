#include <stdio.h>
#include "us314.h"
#include "us315.h"

int cap_x=10;
int cap_y=10;
int cap_z=10;
int cont[10][10][10]; 
int *ptr = &cont[0][0][0];
int posx;
int posy;
int posz;
int array_of_positions[10000];
int *pointer2 = &array_of_positions[0];
int n;
int max_cap;

int main(){
	FILE *file;
	FILE *file2;
	FILE *file3;
	FILE *out_file1;
	FILE *out_file2;
	FILE *out_file3;
	FILE *out_file4;
	int id;
	int x;
	int y;
	int z;
	
	// US313
	
	file=fopen("containers.txt","r");
	if(file==NULL){
		perror("fopen()");
		return 1;
	}
	
	out_file1=fopen("us313.txt","w");
	
	for(int i=0;i<cap_x;i++){
		for(int j=0;j<cap_y;j++){
			for(int k=0;k<cap_z;k++){
				cont[i][j][k]=0;
				ptr++;
			}
		}
	}
		
	while(!feof(file)){
		fscanf(file, "%d %d %d %d", &id, &x, &y, &z);
		cont[x][y][z]=id;
	}
	
	fclose(file);
	
	ptr = &cont[0][0][0];
	for(int i=0;i<cap_x;i++){
		fprintf(out_file1, "x=%d\n",i);
		for(int j=0;j<cap_y;j++){
			for(int k=0;k<cap_z;k++){
				fprintf(out_file1, "%d ",cont[i][j][k]);
				ptr++;
			}
			fprintf(out_file1, "\n");
		}
	}
	
	fclose(out_file1);
	
	//US314
	
	ptr = &cont[0][0][0];
	max_cap=cap_x*cap_y*cap_z;
	
	long slots=free_occupied();
	int *ptroccupied=(int*)&slots;
	int *ptrfree=(int*)&slots+1;
	
	out_file2=fopen("us314.txt","w");
	fprintf(out_file2, "Free slots in the transport vehicle: %d\nOccupied slots in the transport vehicle: %d\n", *ptrfree, *ptroccupied);
	fclose(out_file2);
	
	// US315
	
	ptr = &cont[0][0][0];
	
	file2=fopen("verifyPosition.txt","r");
	if(file2==NULL){
		perror("fopen()");
		return 1;
	}
	
	out_file3=fopen("us315.txt","w");

	
	while(!feof(file2)){
		fscanf(file2, "%d %d %d", &posx, &posy, &posz);
	}
	
	fclose(file2);
	
	int isContainer = containerposition();
	if (isContainer == 1){
		fprintf(out_file3, "The position %d %d %d is occupied.\n", posx, posy, posz);
	}
	else if (isContainer == 0){
		fprintf(out_file3, "The position %d %d %d is free.\n", posx, posy, posz);
	}else
		fprintf(out_file3, "The position %d %d %d does not exist in the ship.\n", posx, posy, posz);
	
	fclose(out_file3);
	
	// US316
	
	file3=fopen("setOfPositions.txt","r");
	if(file3==NULL){
		perror("fopen()");
		return 1;
	}
	
	out_file4=fopen("us316.txt","w");
	
	int count = 0;
	n = 0;
	
	while(!feof(file3)){
		fscanf(file3, "%d %d %d", &posx, &posy, &posz);
		if ((posx < 0 || posx > cap_x) || (posy < 0 || posy > cap_y) || (posz < 0 || posz > cap_z))
		{
			fprintf(out_file4, "The position %d %d %d does not exist in the ship.\n", posx, posy, posz);
		}else{
			array_of_positions[count] = posx;
			array_of_positions[count+1] = posy;
			array_of_positions[count+2] = posz;
			count += 3;
			n++;
		}
	}
	
	fclose(file3);
	
	int number_of_positions = setofpositions();
	
	fprintf(out_file4, "\nFor the given set of positions, the total number of occupied slots is: %d", number_of_positions);

	fclose(out_file4);
	
	return 0;
}
