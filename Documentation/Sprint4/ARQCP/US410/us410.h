#ifndef ASM_H
#define ASM_H
typedef struct {
	int id;
	char pos_x, pos_y, pos_z, refrigerated;
	float dim_x, dim_y, dim_z;
	char outer[20], middle[20], interior[20];
	float k_out, k_mid, k_int, out_th, mid_th, int_th, temp_req;
} cont;
char us410(cont *ptr, char x, char y, char z, int size, int *i);
#endif
