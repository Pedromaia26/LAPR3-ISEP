.section .data

.section .text
.global us410

us410:
	#ptr in %rdi, pos_x in %sil, pos_y in %dl, pos_z in %cl, size in %r8d
	
	movq $2, %rax
	movb %cl, %r9b
	movl %r8d, %ecx
	
struct_loop:

	check_x:
		addq $4, %rdi
		movb (%rdi), %bl
		cmpb %sil, %bl
		je check_y
		addq $104, %rdi
		loop struct_loop
		jmp end
		
	check_y:
		incq %rdi
		movb (%rdi), %bl
		cmpb %dl, %bl
		je check_z
		addq $103, %rdi
		loop struct_loop
		jmp end
		
	check_z:
		incq %rdi
		movb (%rdi), %bl
		cmpb %r9b, %bl
		je check_ref
		addq $102, %rdi
		loop struct_loop
		jmp end
		
	check_ref:
		incq %rdi
		movb (%rdi), %al
	
end:
	ret
