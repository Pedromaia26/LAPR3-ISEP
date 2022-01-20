#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "us410.h"

#define current_temp 20

typedef struct {
	int id;
	char pos_x, pos_y, pos_z, refrigerated;
	float dim_x, dim_y, dim_z;
	char outer[20], middle[20], interior[20];
	float k_out, k_mid, k_int, out_th, mid_th, int_th, temp_req;
} cont;

int main(void) {
	int id;
	char pos_x, pos_y, pos_z, refrigerated;
	float dim_x, dim_y, dim_z;
	char outer[20], middle[20], interior[20];
	float k_out, k_mid, k_int, out_th, mid_th, int_th, temp_req;
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
	char *ptr2=(char*)ptr; 
	int size=1;
	char *ref;
	while(!feof(file)){
		ptr=(cont *)realloc(aux, size*sizeof(cont));
		
		fscanf(file, "%d %hhd %hhd %hhd %hhd %f %f %f %s %s %s %f %f %f %f %f %f %f", &id, &pos_x, &pos_y, &pos_z, &refrigerated, &dim_x, &dim_y, &dim_z, outer, middle, interior, &k_out, &k_mid, &k_int, &out_th, &mid_th, &int_th, &temp_req);
		ptr -> id = id;
		ptr -> pos_x = pos_x;
		ptr -> pos_y = pos_y;
		ptr -> pos_z = pos_z;
		ptr -> refrigerated = refrigerated;
		ptr -> dim_x = dim_x;
		ptr -> dim_y = dim_y;
		ptr -> dim_z = dim_z;
		strcpy(ptr -> outer, outer);
		strcpy(ptr -> middle, middle);
		strcpy(ptr -> interior, interior);
		ptr -> k_out = k_out;
		ptr -> k_mid = k_mid;
		ptr -> k_int = k_int;
		ptr -> out_th = out_th;
		ptr -> mid_th = mid_th;
		ptr -> int_th = int_th;
		ptr -> temp_req = temp_req;
		
		if(refrigerated==1){
			ref="Yes";
		}else ref="No";
		
		fprintf(out_file1, "Container id: %d\nX coordinate: %d\nY coordinate: %d\nZ coordinate: %d\nLength: %.2f m\nWidth: %.2f m\nHeight: %.2f m\nRefrigerated: %s\nOuter walls material: %s\nMiddle layers material: %s\nInterior walls material: %s\nOuter walls thickness: %.2f mm\nMiddle layers thickness: %.2f mm\nInterior walls thickness: %.2f mm\n",ptr -> id, ptr -> pos_x, ptr -> pos_y, ptr -> pos_z, ptr -> dim_x, ptr -> dim_y, ptr -> dim_z, ref, ptr -> outer, ptr -> middle, ptr -> interior, ptr -> out_th, ptr -> mid_th, ptr -> int_th);
		if(strcmp(ref,"Yes")==0){
			fprintf(out_file1, "Required temperature inside the container: %.2f C\n", ptr -> temp_req);
		}
		fprintf(out_file1, "\n");
		
		size++;
		ptr+=size;
		aux=ptr-size;
	}
	
	fclose(file);
	fclose(out_file1);
	
	
	
	//US410
	
	char x,y,z;
	
	file1=fopen("check_position.txt","r");
	if(file1==NULL){
		perror("fopen()");
		return 1;
	}
	fscanf(file1, "%hhd %hhd %hhd", &x, &y, &z);
	
	out_file2=fopen("us410.txt","w");
	
	int area;
	int R_out;
	int R_mid;
	int R_int;
	int Rt;
	int heat;
	
	if(us410(ptr2, x, y, z)==1){
		fprintf(out_file2, "The container is refrigerated.\n");
		
		area=(ptr->dim_x)*(ptr->dim_y)*(ptr->dim_z);
		area=((ptr->dim_x)*(ptr->dim_y)*2)+((ptr->dim_x)*(ptr->dim_z)*2)+((ptr->dim_y)*(ptr->dim_z)*2);
		R_out=(ptr->out_th)/(ptr->k_out*area);
		R_mid=(ptr->mid_th)/(ptr->k_mid*area);
		R_int=(ptr->int_th)/(ptr->k_int*area);
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
