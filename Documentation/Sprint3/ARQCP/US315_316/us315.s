.section .data
    .global ptr
    .global posx
    .global posy
    .global posz
    .global cap_x
    .global cap_y
    .global cap_z
    .global pointer2
    .global n
    
.section .text
    .global containerposition
    .global setofpositions
    
containerposition:
		movq $0, %rax
		movq $0, %rcx
		movq $0, %rdx
		movq $0, %rsi
		movq ptr(%rip), %rdi
		movl posx(%rip), %ecx
		cmpl cap_x(%rip), %ecx
		jge invalid
		cmpl $0, %ecx
		jl invalid
		movl posy(%rip), %edx
		cmpl cap_y(%rip), %edx
		jge invalid
		cmpl $0, %edx
		jl invalid
		movl posz(%rip), %esi
		cmpl cap_z(%rip), %esi
		jge invalid
		cmpl $0, %esi
		jl invalid
		
		
		imull cap_x(%rip), %ecx
		imull cap_y(%rip), %ecx
		imull $4, %ecx
		
		imull cap_x(%rip), %edx
		imull $4, %edx
		
		imull $4, %esi
		
		addq %rcx, %rdi
		addq %rdx, %rdi
		addq %rsi, %rdi
        cmpl $0, (%rdi)
        jne find
		jmp end
	
    find:
		movl $1, %eax
		jmp end
		
	invalid:
		movl $-1, %eax
		
	end:
		ret
		
setofpositions:

		movq pointer2(%rip), %rdi
		movl n(%rip), %ecx
		movl $0, %edx
		
		
	set_of_positons_loop:
	
		movl (%rdi), %r9d
		movl %r9d, posx(%rip)
		addq $4, %rdi
		movl (%rdi), %r10d
		movl %r10d, posy(%rip)
		addq $4, %rdi
		movl (%rdi), %r11d
		movl %r11d, posz(%rip)
		addq $4, %rdi
		
		pushq %rdi
		pushq %rcx
		pushq %rdx 
		
		call containerposition
		popq %rdx
		popq %rcx
		popq %rdi
		
		cmp $1, %eax 
		je increment
	
	
	loop set_of_positons_loop
	
	jmp end2
		

increment:

	incl %edx
	loop set_of_positons_loop

end2:
	movl %edx, %eax
	ret	
