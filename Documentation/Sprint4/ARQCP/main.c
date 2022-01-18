#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "us410.h"

#define current_temp 20

typedef struct {
	int id, pos_x, pos_y, pos_z, dim_x, dim_y, dim_z, refrigerated;
	char outer[20], middle[20], interior[20];
	int out_th, mid_th, int_th, temp_req;
} cont;

int main(void) {
	int id, pos_x, pos_y, pos_z, dim_x, dim_y, dim_z, refrigerated;
	char outer[20], middle[20], interior[20];
	int out_th, mid_th, int_th, temp_req;
	FILE *file;
	FILE *file1;
	FILE *out_file1;
	FILE *out_file2;
	
	
	file=fopen("containers.txt","r");
	if(file==NULL){
		perror("fopen()");
		return 1;
	}
		
	out_file1=fopen("us409.txt","w");
	
	cont *ptr=NULL, *aux=NULL;
	ptr=(cont *) malloc(sizeof(cont));
	aux=ptr;
	int size=1;
	char *ref;
	while(!feof(file)){
		ptr=(cont *)realloc(aux, size*sizeof(cont));
		
		fscanf(file, "%d %d %d %d %d %d %d %d %s %s %s %d %d %d %d", &id, &pos_x, &pos_y, &pos_z, &dim_x, &dim_y, &dim_z, &refrigerated, outer, middle, interior, &out_th, &mid_th, &int_th, &temp_req);
		ptr -> id = id;
		ptr -> pos_x = pos_x;
		ptr -> pos_y = pos_y;
		ptr -> pos_z = pos_z;
		ptr -> dim_x = dim_x;
		ptr -> dim_y = dim_y;
		ptr -> dim_z = dim_z;
		ptr -> refrigerated = refrigerated;
		strcpy(ptr -> outer, outer);
		strcpy(ptr -> middle, middle);
		strcpy(ptr -> interior, interior);
		ptr -> out_th = out_th;
		ptr -> mid_th = mid_th;
		ptr -> int_th = int_th;
		ptr -> temp_req = temp_req;
		
		if(refrigerated==1){
			ref="Yes";
		}else ref="No";
		
		fprintf(out_file1, "Container id: %d\nX coordinate: %d\nY coordinate: %d\nZ coordinate: %d\nLength: %d\nWidth: %d\nHeight: %d\nRefrigerated: %s\nOuter walls material: %s\nMiddle layers material: %s\nInterior walls material: %s\nOuter walls thickness: %d\nMiddle layers thickness: %d\nInterior walls thickness: %d\n",ptr -> id, ptr -> pos_x, ptr -> pos_y, ptr -> pos_z, ptr -> dim_x, ptr -> dim_y, ptr -> dim_z, ref, ptr -> outer, ptr -> middle, ptr -> interior, ptr -> out_th, ptr -> mid_th, ptr -> int_th);
		if(strcmp(ref,"Yes")==0){
			fprintf(out_file1, "Required temperature inside the container: %d C\n", ptr -> temp_req);
		}
		fprintf(out_file1, "\n");
		
		size++;
		ptr+=size;
		aux=ptr-size;
	}
	
	fclose(file);
	fclose(out_file1);
	
	
	
	//US410
	
	int x,y,z;
	
	file1=fopen("check_position.txt","r");
	if(file1==NULL){
		perror("fopen()");
		return 1;
	}
	fscanf(file1, "%d %d %d", &x, &y, &z);
	
	out_file2=fopen("us410.txt","w");
	
	int area;
	int k_out=1;		// ???
	int k_mid=2;		// ???
	int k_int=2;		// ???
	int R_out;
	int R_mid;
	int R_int;
	int Rt;
	int heat;
	
	//ptr a apontar para o inicio do array de structs
	if(us410(ptr->pos_x, x, y, z)==1){
		fprintf(out_file2, "The container is refrigerated.\n");
		
		area=(ptr->dim_x)*(ptr->dim_y)*(ptr->dim_z);
		area=((ptr->dim_x)*(ptr->dim_y)*2)+((ptr->dim_x)*(ptr->dim_z)*2)+((ptr->dim_y)*(ptr->dim_z)*2);
		R_out=(ptr->out_th)/(k_out*area);
		R_mid=(ptr->mid_th)/(k_mid*area);
		R_int=(ptr->int_th)/(k_int*area);
		Rt=R_out+R_mid+R_int;
		
		heat=(current_temp-temp_req)/Rt;
		
		fprintf(out_file2, "It will be necessary to provide the container %d J (%d W*s).\n",heat, heat);
		
	}else{
		fprintf(out_file2, "The container is not refrigerated.\n");
	}
	
	fclose(file1);
	fclose(out_file2);
	
	
	
	free(ptr);
	return 0;
}
