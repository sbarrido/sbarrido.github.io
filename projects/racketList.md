## Lists and Tree Manipulations
Processing and manipulating data stored in lists and trees. Featuers replacing, counting, and containing?
functions
```
;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname Hw7) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require 2htdp/image)
;===========================Ex 1============================
;a Mobile is one of:
; - (make-leaf Number)
; - (make-rod Mobile Number Number Mobile)
(define-struct leaf [weight])
(define-struct rod [lm ld rd rm])

(define rod1 (make-rod (make-leaf 5)
                       5 5
                       (make-leaf 5)))
(define leaf1 (make-leaf 1))
(define rod2 (make-rod (make-rod (make-leaf 5)
                                 10 10
                                 (make-leaf 5))
                       20 20
                       (make-leaf 20)))

;weight: Mobile -> Number
;produces weight of given Mobile
(define (weight m)
  (cond
    [(leaf? m) (leaf-weight m)]
    [else (+ (weight (rod-lm m)) (weight (rod-rm m)))]))

;wt-change: Mobile Function Number -> Mobile
;produces a new Mobile given a Mobile, Function(/,+,-,*...etc) and a Number
(define (wt-change m math n)
  (cond
    [(leaf? m) (make-leaf (math (weight m) n))]
    [(rod? m)
     (cond
       [(leaf? (rod-rm m)) (make-rod (wt-change (rod-lm m) math n)
                                     (rod-ld m) (rod-rd m)
                                     (make-leaf (math (weight (rod-rm m)) n)))]
       [(leaf? (rod-lm m)) (make-rod (make-leaf (math (weight (rod-rm m)) n))
                                     (rod-ld m) (rod-rd m)
                                     (wt-change (rod-rm m) math n))]
       [else (make-rod (wt-change (rod-lm m) math n)
                       (rod-ld m) (rod-rd m)
                       (wt-change (rod-rm m) math n))])]))
;examples
(check-expect (wt-change rod1 + 2) (make-rod (make-leaf 7)
                                             5 5
                                             (make-leaf 7)))
(check-expect (wt-change leaf1 - 2) (make-leaf -1))
(check-expect (wt-change rod2 * 2) (make-rod (make-rod (make-leaf 10)
                                 10 10
                                 (make-leaf 10))
                       20 20
                       (make-leaf 40)))
;lighten: Mobile Number -> Mobile
;produces a new Mobile with Weight changed by a factor of the given Number
(define (lighten m n)
  (wt-change m / n))
(check-expect (lighten rod1 2) (make-rod (make-leaf 2.5)
                                         5 5
                                         (make-leaf 2.5)))
(check-expect (lighten leaf1 2) (make-leaf .5))
(check-expect (lighten rod2 2) (make-rod (make-rod (make-leaf 2.5)
                                                   10 10
                                                   (make-leaf 2.5))
                                         20 20
                                         (make-leaf 10)))

;enlarge: Mobile Number -> Mobile
; produces a new Mobile with Weight changed by a factor of the given Number
(define (enlarge m n)
  (wt-change m * n))
(check-expect (enlarge rod1 2) (make-rod (make-leaf 10)
                                         5 5
                                         (make-leaf 10)))
(check-expect (enlarge leaf1 2) (make-leaf 2))
(check-expect (enlarge rod2 2) (make-rod (make-rod (make-leaf 10)
                                                   10 10
                                                   (make-leaf 10))
                                         20 20
                                         (make-leaf 40)))
                                            

;===============================Ex 2======================================
;a NEList-of-temperatures is one of:
; - (cons Ctemperature empty)
; - (cons CTemperature NEList-of-temperatures)
;(define NEListT (cons Ctemprature (cons Ctemperature empty)))
;(define NEListT1 (cons Ctemperature empty))

;a NEList-of-Booleans is one of:
; - (cons Boolean empty)
; - (cons Boolean NEList-of-Booleans)
;(define NEListB (cons Boolean (cons Boolean empty)))
;(define NEListB1 (cons Boolean empty))

;a [NEList-of X] is one of:
; - (cons X empty)
; - (cons X [NEList-of X])
;(define NEListX (cons X (cons X empty)))
;(define NEListX1 (cons X empty))

;==================================EX 3===================================
;closer?: [X] [X -> X] input1 input2 -> [X]
;[X -> X] are following functions:
; - string-length
; - length
; - dist
(define (i n)
  (circle n "solid" "red"))
(define lol1 (list (list (i 1) (i 2) (i 3)) (list (i 2) (i 3)) (list '())))
(define los1 (list "happy" "hi" "bye" "donkey"))
(define lop1 (list (make-posn 1 1) (make-posn 2 2) (make-posn 3 3)))

;dist: Posn -> Number
;produces distance of Posn from 0,0
(define (dist p)
  (sqrt (+ (expt (posn-x p) 2) (expt (posn-y p) 2))))
(check-expect (dist (make-posn 3 4)) 5)

;closer?: [X] [X -> X] [ListOf X] -> [X]
;returns the shortest/smallest item in a [ListOf X]
(define (closer? func list)
  (cond
    [(empty? list) empty]
    [(empty? (rest list)) (first list)]
    [else
     (let ([ i (first list)])
       (if (<= (func i) (func (first (rest list))))
           i
           (closer? func (rest list))))]))
(check-expect (closer? dist lop1) (make-posn 1 1))
(check-expect (closer? string-length los1) "hi")
(check-expect (closer? length lol1) (list '()))

;shortest-string: ListOfStrings -> String
;returns the shortest String from a ListOfStrings
(define (shortest-string los)
  (closer? string-length los1))
(check-expect (shortest-string los1) "hi")
;closest-posn: ListOfPosn -> Posn
;Returns the Posn closest to 0,0 in a given list of ListOfPosn
(define (closest-posn lop)
  (closer? dist lop))
(check-expect (closest-posn lop1) (make-posn 1 1))
;shortest-list: ListOf(ListOfImages) -> ListOfImages
;Returns the shortest ListOfImages from the given ListOf(ListOfImages)
(define (shortest-list lol)
  (closer? length lol))
(check-expect (shortest-list lol1) (list '()))
  

;===============================EX 4===================================
;remove-num:[X] [X->Y] X -> Y
;Returns a new list of things after being affected by a function(even? or empty?)
(define lon1 (list 1 2 3 4 5 6 7 8 9 10))
(define loln1 (list '(2 3) '() '(4 5)))

(define (remove-num func list)
  (cond
    [(empty? list) empty]
    [else
         (let ([ i (first list)])
           (if (func i)
               (remove-num func (rest list))
               (cons i (remove-num func (rest list)))))]))
(check-expect (remove-num even? lon1) (list 1 3 5 7 9))
(check-expect (remove-num empty? loln1) (list (list 2 3) (list 4 5)))

;remove-even: ListOfNumbers -> ListOfNumbers
;returns ListOfNumbers with no even Numbers
(define (remove-even lon)
  (remove-num even? lon))
(check-expect (remove-even lon1) (list 1 3 5 7 9))

;remove-empty: ListOfListOfNumbers -> ListOfListOfNumbers
;returns a ListOfListOfNumbers with no empty ListOfNumbers
(define (remove-empty loln)
  (remove-num empty? loln))
(check-expect (remove-empty loln1) (list (list 2 3) (list 4 5)))
  
;==========================EX 5====================================
; Number -> [List-of Number]
; tabulates sin between n 
; and 0 (incl.) in a list
(define (tab-sin n)
  (tabulate sin n))
(check-within (tab-sin 3) (list (sin 3) (sin 2) (sin 1) (sin 0)) .1)
	
; Number -> [List-of Number]
; tabulates sqrt between n 
; and 0 (incl.) in a list
(define (tab-sqrt n)
  (tabulate sqrt n))
(check-within (tab-sqrt 2) (list (sqrt 2) (sqrt 1) (sqrt 0)) .1)

;tabulate: [X->X] X -> [ListOf X]
;creates a list of values with the given Function and Number
;func is one of:
; - sin
; - sqrt
(define (tabulate func n)
  (cond
    [(= n 0) (list (func 0))]
    [else
     (cons (func n)
           (tabulate func (sub1 n)))]))

(check-within (tab-sin 3) (list (sin 3) (sin 2) (sin 1) (sin 0)) .1)
(check-within (tab-sqrt 2) (list (sqrt 2) (sqrt 1) (sqrt 0)) .1)

;===================================EX 6=====================================
;has?: [X X-> Bool] [ListOf X] -> [ListOf X]
;returns a [ListOf X] that removes all elements of the list that return #true
;with the given function
;function is one of:
; - <
; - string=?
; - empty?

(define (has? func list id)
  (cond
    [(equal? func empty?)
     (cond
       [(empty? list) empty]
       [else
        (let [(i (first (first list)))]
          (if (empty? i)
              (has? func (rest list) id)
              (cons (first list) (has? func (rest list) id))))])]
     
    [(empty? list) empty]
    [(empty? (rest list)) list]
    [else
     (let [(i (first list))]
       (if (func i id)
           (has? func (rest list) id)
           (cons i (has? func (rest list) id))))]))
(check-expect (has? < lon1 5) (list 5 6 7 8 9 10))
(check-expect (has? string=? los1 "happy") (list "hi" "bye" "donkey"))
(check-expect (has? empty? lol1 empty) (list (list (i 1) (i 2) (i 3)) (list (i 2) (i 3))))

;has-<: [ListOf Numbers] Number-> [ListOf Numbers]
;produces ListOfNumbers with values greater than or equal to given Number
(define (has-< list id)
  (has? < list id))
(check-expect (has-< lon1 5) (list 5 6 7 8 9 10))

;has-string=?: [ListOf Strings] String -> [ListOf Strings]
; produces ListofStrings with Strings not equal to given String
(define (has-string=? list id)
  (has? string=? list id))
(check-expect (has-string=? los1 "happy") (list "hi" "bye" "donkey"))

;has-empty?: [ListOf ListOfImages] -> [ListOf ListOfImages]
;produces a [ListOf ListOfImages] with no empty lists
(define (has-empty? list id)
  (has? empty? list empty))
(check-expect (has-empty? lol1 empty) (list (list (i 1) (i 2) (i 3)) (list (i 2) (i 3))))

;=======================Ex 7=======================================
;multiply: ListOfNumbers Number -> ListOfNumbers
;returns a ListOfNumbers that is comprised of the orignal multiplied by the given Number

(define (multiply lon n)
  (local
    [;math: original-> new
     ;produces new number through multipication
     (define (math o)
       (* o n))
     ]
    (map math lon)))

(check-expect (multiply lon1 1) (list 1 2 3 4 5 6 7 8 9 10))

;mul-table: ListOfNumbers -> ListOfLists
;produces a list of list of numbers multiplied by the given list of numbers
(define (mul-table lon)
  (local
    [(define (maybe o)
       (* o (first lon)))
     ]
    (map maybe lon)))

;(check-expect (mul-table (list 2 3 5 10)) (list
 ;                                          (list 4 6 10 20)
  ;                                         (list 6 9 15 30)
   ;                                        (list 10 15 25 50)
    ;                                       (list 20 30 50 100)))


;==============================Ex 8===================================
;; A TreeOfNumber is one of:
;;  - (make-leaf Number)
;;  - (make-node1 Number TreeOfNumber)
;;  - (make-node2 Number TreeOfNumber TreeOfNumber)
(define-struct leafa [n])
(define-struct node1 [n tree])
(define-struct node2 [n tree1 tree2])

(define leaf-a (make-leafa 5))
(define node1-a (make-node1 5 (make-leafa 5)))
(define node2-a (make-node2 5 (make-leafa 5) node1-a))

;sum-tree: TreeOfNumber -> Number
;sums all of the Number in a given TreeOfNumber
(define (sum-tree tree)
 (process-tree + tree "sum"))
(check-expect (sum-tree leaf-a) 5)
(check-expect (sum-tree node1-a) 10)
(check-expect (sum-tree node2-a) 20)

;prod-tree: TreeOfNumber -> Number
;product all of the Number in a given TreeOfNumber
(define (prod-tree tree)
  (process-tree * tree "prod"))
(check-expect (prod-tree leaf-a) 5)
(check-expect (prod-tree node1-a) 25)
(check-expect (prod-tree node2-a) 625)

;=========================Ex 9 ==================================
;op-tree: [X -> Y] -> Y
;Calculates an operation on a given X
;func are one of folloiwng:
; - +
; - *
(define (op-tree func tree)
  (cond
    [(leafa? tree) (leafa-n tree)]
    [(node1? tree) (func (node1-n tree) (op-tree func (node1-tree tree)))]
    [(node2? tree) (func (node2-n tree)
                      (op-tree func (node2-tree1 tree))
                      (op-tree func (node2-tree2 tree)))]))
(check-expect (op-tree * leaf-a) 5)
(check-expect (op-tree + leaf-a) 5)
(check-expect (op-tree * node1-a) 25)
(check-expect (op-tree + node1-a) 10)
(check-expect (op-tree * node2-a) 625)
(check-expect (op-tree + node2-a) 20)

;========================Ex 10=================================
;count-tree: TreeOfNumber -> Number
;Counts how many leaves are in a TreeOfNumber
(define (count-tree tree)
  (process-tree + tree "count"))
(check-expect (count-tree leaf-a) 1)
(check-expect (count-tree node1-a) 1)
(check-expect (count-tree node2-a) 2)

;===================Ex 11===================================
;process-tree: [X -> Y] [X] String -> Y
;Depending on given String, do an operation on given X
;func is one of the following:
; - +
; - *
;op? is one of the following:
; - sum
; - prod
; - count
(define (process-tree func tree op?)
  (cond
    [(and (or (string=? "sum" op?)
              (string=? "prod" op?))
          (leafa? tree))
     (leafa-n tree)]
    [(and (or (string=? "sum" op?)
              (string=? "prod" op?))
          (node1? tree))
     (func (node1-n tree)
           (process-tree func (node1-tree tree) op?))]
    [(and (or (string=? "sum" op?)
              (string=? "prod" op?))
          (node2? tree))
     (func (node2-n tree)
           (process-tree func (node2-tree1 tree) op?)
           (process-tree func (node2-tree2 tree) op?))]
    [(and (string=? "count" op?)
          (leafa? tree))
     (add1 0)]
    [(and (string=? "count" op?)
          (node1? tree))
     (process-tree func (node1-tree tree) op?)]
    [(and (string=? "count" op?)
          (node2? tree))
     (+ (process-tree func (node2-tree1 tree) op?)
        (process-tree func (node2-tree2 tree) op?))]))
;Aspects of op-tree are present within process-tree, however process-tree
;has conditions set to handle more parameters. Gives more control to the user
;when using process-tree.

;=================================EX 12===================================
; a [TreeOf X] is one of:
; - (make-leaf X)
; - (make-node1 X [TreeOf X])
; - (make-node2 X [TreeOf X] [TreeOf X])

;================================EX 13==================================
(define (process-tree1 func tree op?)
  (cond
    [(and (or (string=? "sum" op?)
              (string=? "prod" op?))
          (leafa? tree))
     (leafa-n tree)]
    [(and (or (string=? "sum" op?)
              (string=? "prod" op?))
          (node1? tree))
     (func (node1-n tree)
           (process-tree func (node1-tree tree) op?))]
    [(and (or (string=? "sum" op?)
              (string=? "prod" op?))
          (node2? tree))
     (func (node2-n tree)
           (process-tree func (node2-tree1 tree) op?)
           (process-tree func (node2-tree2 tree) op?))]
    [(and (string=? "count" op?)
          (leafa? tree))
     (add1 0)]
    [(and (string=? "count" op?)
          (node1? tree))
     (process-tree func (node1-tree tree) op?)]
    [(and (string=? "count" op?)
          (node2? tree))
     (+ (process-tree func (node2-tree1 tree) op?)
        (process-tree func (node2-tree2 tree) op?))]))
;no changes should be needed to process any [TreeOf X] because they share
;the same format. The only potential changes may be the functions on X

;=================================ex 14================================
;count-tree is a part of process-tree. as such nothing needs to be changed in
;the body of the function.
```
