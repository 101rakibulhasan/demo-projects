.model small
.stack 100h

.data
    days1 DB "Saturday$"
    days2 DB "Sunday$"
    days3 DB "Monday$"
    days4 DB "Tuesday$"
    days5 DB "Wednesday$"
    days6 DB "Thursday$"
    days7 DB "Friday$"
    msg1 db 10, 13, "Enter the day's serial number (1-7): $"
    msg2 db 10, 13, "The day is: $"

.code
main proc
    mov ax, @data
    mov ds, ax

get:
    mov ah, 9
    lea dx, msg1
    int 21h

    mov ah, 1
    int 21h
    sub al, '0'

    cmp al, 1
    jl wr
    cmp al, 7
    jg wr   
    
    mov bl, al

    mov ah, 9
    lea dx, msg2
    int 21h 
    
    mov al, bl
    
    cmp al, 1
    je sat
    
    cmp al, 2
    je sun
    
    cmp al, 3
    je mon 
    
    cmp al, 4
    je tue
    
    cmp al, 5
    je wed
    
    cmp al, 6
    je thu
    
    cmp al, 7
    je fri

    

    jmp exit

wr:
    mov ah, 9
    lea dx, msg1
    int 21h

    jmp get
    
sat:
    mov ah, 9
    lea dx, days1
    int 21h  
    
    jmp exit
sun:
    mov ah, 9
    lea dx, days2
    int 21h 
    jmp exit  
    
mon:
    mov ah, 9
    lea dx, days3
    int 21h
    jmp exit 
    
tue:
    mov ah, 9
    lea dx, days4
    int 21h
    jmp exit 
    
wed:
    mov ah, 9
    lea dx, days5
    int 21h 
    jmp exit
    
thu:
    mov ah, 9
    lea dx, days6
    int 21h 
    jmp exit 
    
fri:
    mov ah, 9
    lea dx, days7
    int 21h 
    jmp exit
    
exit:
    mov ah, 4Ch
    int 21h

main endp
end main