## Recursion
Exploration of Mutual and Generative Recursion. Examples of "tree" processing.

```
;;;;;;;;;;;;;;;;;;;;;;;Warmup;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;==========================EX 1=========================================
; A Mobile is one of:
; - (make-leaf Number)
; - (make-rod Mobile Number Number Mobile)
(define-struct leaf [weight])
(define-struct rod [lm ld rd rm])

(define leaf1 (make-leaf 8))
(define leaf2 (make-leaf 2))
(define rod1 (make-rod leaf1 2 2 leaf1))
(define rod2 (make-rod rod1 2 2 leaf1))

(define lom1 (list rod1 rod2))

;count?: Mobile Number -> Number
;Produces a Number of Leaves that are higher than the given Number within a Mobile
(define (count? m n)
  (cond
    [(leaf? m)
     (if (> (leaf-weight m) n)
         1
         0)]
    [else (+ (count? (rod-lm m) n)
             (count? (rod-rm m) n))]))
(check-expect (count? leaf1 3) 1)
(check-expect (count? leaf1 9) 0)
(check-expect (count? rod1 3) 2)


;heavy-leaves: [Mobile] Number -> Number
;Returns the Number of Leaves heavier than the given weight
(define (heavy-leaves lom n)
  (cond
    [(empty? lom) 0]
    [else
     (let [(i (first lom))]
       (+ (count? i n) (heavy-leaves (rest lom) n)))]))
(check-expect (heavy-leaves lom1 3) 5)

;;;;;;;;;;;;;;;;;;;;;;;Book Exercises;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;===========================EX 323===========================
(define-struct no-info [])
(define NONE (make-no-info))
 
(define-struct node [ssn name left right])
; A BinaryTree (short for BT) is one of:
; – NONE
; – (make-node Number Symbol BT BT)

;ssn?: Number Node -> Symbol
;produces the symbol of matching ssn and Number
(define (snn? n nd)
  (let [(i (node-ssn nd))]
    (if (= n i)
        (cons (node-name nd)
              (append (search-bt n (node-left nd))
                      (search-bt n (node-right nd))))
        (append (search-bt n (node-left nd))
                (search-bt n (node-right nd))))))

;search-bt: Number BinaryTree -> [Symbol]
;Produces the Symbol-field of a node with the given BT
(define (search-bt n bt)
  (cond
    [(node? bt) (snn? n bt)]
    [else empty]))
  
(check-expect (search-bt 15 (make-node 15
                                       'd
                                       NONE
                                       (make-node 24
                                                  'i
                                                  NONE
                                                  NONE)))
             (list 'd))
(check-expect (search-bt 24 (make-node 15
                                       'd
                                       NONE
                                       (make-node 24
                                                  'i
                                                  NONE
                                                  NONE)))
              (list 'i))
;(check-expect (search-bt 17 (make-node 15
;                                       'd
;                                       NONE
;                                       (make-node 24
;                                                  'i
;                                                  NONE
;                                                  NONE)))
;              #false)

;=============================EX 388===================================
;Employee is (make-employee String Number Number)
(define-struct employee [name ssn pay-rate])
;work-record is (make-work-record String Number)
(define-struct work-record [name hours])
;Employee-wage is (make-employee-wage String Number)
(define-struct employee-wage [name wage])

(define emp1 (make-employee "John" 55 25))
(define emp2 (make-employee "Alex" 20 100))
(define wr1 (make-work-record "John" 2))
(define wr2 (make-work-record "Alex" 4))
(define emp-w1 (make-employee-wage "John" 50))
(define emp-w2 (make-employee-wage "Alex" 400))

(define lo-emp1 (list emp1 emp2))
(define lo-wr1 (list wr1 wr2))
(define lo-emp-w (list emp-w1 emp-w2))

; weekly-wage: Number Number -> Number
; computes the weekly wage from pay-rate and hours
(define (weekly-wage pay-rate hours)
  (* pay-rate hours))

;emp-wage: Employee Work-Record -> Employee-Wage
;Produces a (make-employee-wage String Number from given Employee and Work-Record
(define (emp-wage emp wr)
  (make-employee-wage (employee-name emp)
                      (weekly-wage (employee-pay-rate emp)
                                   (work-record-hours wr))))
(check-expect (emp-wage emp1 wr1) emp-w1)
(check-expect (emp-wage emp2 wr2) emp-w2)
      
; wagesAll: [Employee] [Work-Record] -> [Employee-Wage]
;Produces a list of Employee Wages from the given [Employee] and [Work-Record]
(define (wagesAll lo-emp lo-wr)
  (map emp-wage lo-emp lo-wr))

(check-expect (wagesAll lo-emp1 lo-wr1)
              lo-emp-w)

;=============================EX 389================================================
;A PhoneRecord is (make-phone-record String String
(define-struct phone-record [name number])

(define name1 "John")
(define name2 "Alex")
(define pn1 1234)
(define pn2 5678)

(define pr1 (make-phone-record "John" 1234))
(define pr2 (make-phone-record "Alex" 5678))

(define lopr1 (list pr1 pr2))
(define lon1 (list name1 name2))
(define lopn1 (list pn1 pn2))

;record: String Number -> Phone-Record
;Produces a Phone-Record from the given String and Number
(define (record n pn)
  (make-phone-record n pn))
(check-expect (record name1 pn1) pr1)
(check-expect (record name2 pn2) pr2)

;zip: [Names] [Phone Number] -> [Phone-Record]
;Produces a [Phone-Records] from the given [Names] and [Phone Number]
(define (zip lon lopn)
  (map record lon lopn))
(check-expect (zip lon1 lopn1) lopr1)

;==================================EX 390================================
(define-struct branch [left right])
 
; A TOS is one of:
; – Symbol
; – (make-branch TOS TOS)
 
; A Direction is one of:
; – 'left
; – 'right
 
; A list of Directions is also called a path.

;tree-pick: TOS [Directions] -> Boolean
;Produces a Boolean from the given TOS and [Directions]
(define (tree-pick tos lo-d)
  (cond
    [(branch? tos)
     (let [(i (first lo-d))]
     (cond
         [(symbol=? 'left i) (tree-pick (branch-left tos) (rest lo-d))]
         [(symbol=? 'right i) (tree-pick (branch-right tos) (rest lo-d))]))]
    [else #false]))
(check-expect (tree-pick (make-branch (make-branch 'yup
                                                   'yup)
                                      (make-branch 'yup
                                                   (make-branch 'yup
                                                                'yup)))
                         (list 'left
                               'right
                               'left
                               'left))
              #false)

;;;;;;;;;;;;;;;;;;;;;;;Mutually Recursive Data Definition;;;;;;;;;;;;;;;;
(define-struct section (title text subsections))
 
;; A Section is (make-section String [ListOf String] [ListOf Section])
;; where the title field is the name of the section,
;; the text field is the words in the section,
;; and the subsections field is the contents of the section.

;=============================EX 2===========================
(define sec1 (make-section "Bubble"
                           (list "happy"
                                 "hello"
                                 "dinosaur")
                           (list (make-section "Big"
                                                 (list "sad"
                                                       "candy")
                                                 empty)
                                 (make-section "Small"
                                               (list "cat"
                                                     "dog")
                                               empty))
                           ))
(define sec2 (make-section "Elephant"
                           (list "blue"
                                 "orange")
                           (list (make-section "pizza"
                                               (list "yummy")
                                               empty)
                                 (make-section "sticks"
                                               empty
                                               empty))))

;==========================EX 3=====================================
;process-section: Section -> ???
;???
(define (process-section s)
  (...(section-title s)...(section-text s)...(process-los (section-subsections s))...))
;process-los: [Section] -> ???
;???
(define (process-los los)
  (cond
    [(empty? los) empty]
    [else
     (let [(i (first los))]
       (...(process-section i)...(process-los (rest los))...))]))

;===============================EX 4=================================
(define los1 (list "happy" "dandy"))

;same?: [String] String -> Boolean
;Returns a Boolean with the given [String] and String
(define (same? los s)
  (cond
    [(empty? los) false]
    [else
     (if (string=? s (first los))
      true
      (same? (rest los) s))]))

(check-expect (same? los1 "happy") true)
(check-expect (same? los1 "dog") false)

;name?: Section String -> [String]
;Returns the (section-title section) whose [String] contains the given String
(define (list-name? sct s)
  (cond
    [(false? (same? (section-text sct) s))
     (search-section (section-subsections sct) s)]
    [else
     (cons (section-title sct) (search-section (section-subsections sct) s))]
    ))
(check-expect (list-name? (make-section "happy" (list "dog") empty) "dog")
             (list "happy"))
(check-expect (list-name? (make-section "happy" (list "dog") empty) "cat")
             empty)

;search-section: [Section] String -> [String]
;Returns a [String] made from the given Section that matches the given String

(define (search-section losc s)
  (cond
    [(empty? losc) empty]
    [else
     (let [(i (first losc))]
       (append (list-name? i s) (search-section (rest losc) s)))]))

(check-expect (search-section (list (make-section "dandy"
                                                  (list "happy"
                                                        "sad")
                                                  empty)
                                    (make-section "animal"
                                                  (list "cat"
                                                        "dog")
                                                  empty))
                              "cat")
              (list "animal"))
```
