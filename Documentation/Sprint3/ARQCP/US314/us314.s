.section .data
	.global ptr, max_cap

.section .text
	.global free_occupied

free_occupied:
	movq $0, %rax
	movq $0, %rbx
	movq $0, %rcx
	movq $0, %rdx
	movq $0, %rsi
	movq ptr(%rip), %rsi
	movl max_cap(%rip), %ecx
	cmpl $0, %ecx
	jle end
	
arr_loop:
	movl (%rsi), %ebx
	cmpl $0, %ebx
	je add_free
	incq %rdx
	addq $4, %rsi
	loop arr_loop
	
end:
	shlq $32, %rax
	addq %rdx, %rax
	ret
	
add_free:
	incq %rax
	addq $4, %rsi
	loop arr_loop
	jmp end
	

	
