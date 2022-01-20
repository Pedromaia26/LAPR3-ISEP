.section .data

.section .text
.global us410

us410:
	#ptr in %rdi, pos_x in %sil, pos_y in %dl, pos_z in %cl
	
	addq $4, %rdi
	
struct_loop:
	movq $0, %rax
	movb (%rdi), %bl
	cmpb %sil, %bl
	je check_y
	addq $104, %rdi
	jmp struct_loop
	
check_y:
	incq %rdi
	movb (%rdi), %bl
	cmpb %dl, %bl
	je check_z
	addq $103, %rdi
	jmp struct_loop
	
check_z:
	incq %rdi
	movb (%rdi), %bl
	cmpb %cl, %bl
	je check_ref
	addq $102, %rdi
	jmp struct_loop
	
check_ref:
	incq %rdi
	movb (%rdi), %al
	
end:
	ret
