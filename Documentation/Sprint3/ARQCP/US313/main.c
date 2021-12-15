#include <stdio.h>

int main(){
	FILE *file;
	int id;
	int x;
	int y;
	int z;
	
	int max_x=20;
	int max_y=20;
	int max_z=20;
	int cont[max_x][max_y][max_z];
	
	
	int *ptr = &cont[0][0][0];
	for(int i=0;i<max_x;i++){
		for(int j=0;j<max_y;j++){
			for(int k=0;k<max_z;k++){
				cont[i][j][k]=0;
				ptr++;
			}
		}
	}
	
	
	file=fopen("containers.txt","r");
	if(file==NULL){
		perror("fopen()");
		return 1;
	}
	
	while(!feof(file)){
		fscanf(file, "%d %d %d %d", &id, &x, &y, &z);
		cont[x-1][y-1][z-1]=id;
	}
	
	fclose(file);
	
	for(int i=0;i<max_x;i++){
		printf("x=%d\n",i+1);
		for(int j=0;j<max_y;j++){
			for(int k=0;k<max_z;k++){
				printf("%d ", cont[i][j][k]);
				ptr++;
			}
			printf("\n");
		}
	}
	
	return 0;
}
