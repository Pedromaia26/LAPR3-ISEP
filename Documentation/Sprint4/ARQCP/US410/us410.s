.section .data

.section .text
.global us410

us410:
	#ptr in %rdi, pos_x in %sil, pos_y in %dl, pos_z in %cl, size in %r8d, *i in %r9
	
	movq $2, %rax
	movq $0, %r11
	movb %cl, %r10b
	movl %r8d, %ecx
	
struct_loop:

	check_x:
		addq $4, %rdi
		movb (%rdi), %bl
		cmpb %sil, %bl
		je check_y
		incl %r11d
		addq $104, %rdi
		loop struct_loop
		jmp end
		
	check_y:
		incq %rdi
		movb (%rdi), %bl
		cmpb %dl, %bl
		je check_z
		incl %r11d
		addq $103, %rdi
		loop struct_loop
		jmp end
		
	check_z:
		incq %rdi
		movb (%rdi), %bl
		cmpb %r10b, %bl
		je check_ref
		incl %r11d
		addq $102, %rdi
		loop struct_loop
		jmp end
		
	check_ref:
		incq %rdi
		movb (%rdi), %al
	
end:
	movl %r11d, (%r9)
	ret
