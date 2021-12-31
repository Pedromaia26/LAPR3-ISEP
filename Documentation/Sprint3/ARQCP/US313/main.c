#include <stdio.h>

int main(){
	FILE *file;
	int id;
	int x;
	int y;
	int z;
	int cap_x;
	int cap_y;
	int cap_z;
	
	
	file=fopen("containers.txt","r");
	if(file==NULL){
		perror("fopen()");
		return 1;
	}
	
	fscanf(file, "%d %d %d", &cap_x, &cap_y, &cap_z);
	int cont[cap_x][cap_y][cap_z];
	
	int *ptr = &cont[0][0][0];
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
		cont[x-1][y-1][z-1]=id;
	}
	
	fclose(file);
	
	for(int i=0;i<cap_x;i++){
		printf("x=%d\n",i+1);
		for(int j=0;j<cap_y;j++){
			for(int k=0;k<cap_z;k++){
				printf("%d ", cont[i][j][k]);
				ptr++;
			}
			printf("\n");
		}
	}
	
	return 0;
}
