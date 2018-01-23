## Various Racket Functions
A variety of functions created for Racket. Includes Ball movement and DNA sequencing excercises

 ```
 ;;;;;;;;;;;;;;;;;;;;;;;;;Ball On A Table;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;===============================Ex 1===================================
(define psn0 (make-posn 0 0))
(define psn1 (make-posn 2 4))
(define psn2 (make-posn 5 5))
(define x1 1)
(define x2 2)
(define y1 1)
(define y2 2)

;a Ball is (make-ball Posn Number Number)
;x-speed, y-speed are speeds (Number)
(define-struct ball [psn x-speed y-speed])

(define ball-1 (make-ball psn1 x1 y1))
(define ball-2 (make-ball psn2 x2 y2))

;==================================EX 2=================================
;a Table is (make-table Posn Number)
;r, is the radius of the table
(define-struct table [center r])

(define table-1 (make-table psn0 5))
(define table-2 (make-table psn0 10))
; dist: Posn Posn -> Number
;Computes the distance between two posns
(define (dist p0 p1)
  (inexact->exact (sqrt (+ (expt (- (posn-x p0) (posn-x p1)) 2)
                           (expt (- (posn-y p0) (posn-y p1)) 2)))))
(check-within (dist psn2 psn0) (sqrt 50) .1)

;on-table? Ball Table -> Boolean
;Produces True if Ball is on the Table
(define (on-table? b t)
  (if (< (dist (ball-psn b) (table-center t)) (table-r t))
      true
      false))
(check-expect (on-table? ball-1 table-1) true)
(check-expect (on-table? ball-2 table-1) false)

;===============================Ex 3=====================================
;new-posn: Ball Number -> Posn
;Produces a new Posn for the given Ball at the specified time/tick
(define (new-posn b t)
  (make-posn (+ (posn-x (ball-psn b))
                (* (ball-x-speed b) t))
             (+ (posn-y (ball-psn b))
                (* (ball-y-speed b) t))))
(check-expect (new-posn ball-1 1) (make-posn 3 5))
(check-expect (new-posn ball-2 5) (make-posn 15 15))
; move-ball: Ball Number -> Ball
;Produces a Ball with a new Posn after t
(define (move-ball b)
  (make-ball (new-posn b 1)
             (ball-x-speed b) (ball-y-speed b)))
(check-expect (move-ball ball-1)
              (make-ball (make-posn 3 5)
                         1 1))
(check-expect (move-ball ball-2)
              (make-ball (make-posn 7 7)
                         2 2))

;================================Ex 4===================================
;how-long: Ball Table -> Number
;Computes the time it takes to "fall off" (ball distance > table radius)
(define (how-long b t)
  (cond
    [(false? (on-table? b t)) 0]
    [else
     (let [(i (move-ball b))]
       (cond
         [(false? (on-table? i t))
          (add1 0)]
         [else
          (add1 (how-long i t))]))]))
(check-expect (how-long ball-1 table-1) 1)
(check-expect (how-long ball-1 table-2) 4)
(check-expect (how-long ball-2 table-1) 0)
(check-expect (how-long ball-2 table-2) 2)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;DNA Compression;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;=============================Ex 5=======================================
; A DNA is a String
; A rle is a String

;count-dupe: [ListOf String] String -> Number
;Computes the first Number of occurences (consecuvitvely) in a [ListOf String] given a String
(define (count-dupe los s)
  (cond
    [(empty? los) 0]
    [(string=? s (first los))
     (add1 (count-dupe (rest los) s))]
    [else 0]))
(check-expect (count-dupe (explode "AAAAAAAAAA") "A") 10)
(check-expect (count-dupe (explode "GCCCCTTAAAAAAAAAA") "G") 1)
(check-expect (count-dupe (explode "GCCCCTTAAAAAAAAAA") "A") 0)

;count-list: [ListOf String]-> [ListOf Number]
;Produces a list of Number with the given [ListOf String]
(define (count-list los)
  (cond
    [(empty? los) empty]
    [(empty? (rest los)) (cons (count-dupe los (first los))
                               (count-list (rest los)))]
    [else
     (let [(a (first los))
           (b (remove-all-dup (implode los)))]
       (cons (count-dupe los a)
             (count-list (explode b))))]))
(check-expect (count-list (explode "AAABBCCC"))
              (list 3 2 3))
(check-expect (count-list (explode "G"))
              (list 1))
;remove-all-dup: String -> String
;Removes all occurences of the given String from the [ListOf String]
(define (remove-all-dup s)
  (let [(a (explode s))]
    (substring s (count-dupe a (first a)))))
(check-expect (remove-all-dup "AABC")
              "BC")
;remove-dups: [ListOf Stirng] -> [ListOf String]
;removes consecutive duplicates in a list
(define (remove-dups l)
  (cond
    [(empty? l) empty]
    [(empty? (rest l)) l]
    [else
     (let [(i (first l))]
       (if (equal? i (first (rest l)))
           (remove-dups (rest l))
           (cons i (remove-dups (rest l)))))]))
(check-expect (remove-dups (explode "AAAABCCCCAA"))
              (list "A" "B" "C" "A"))
;combine : [ListOf String] [ListOf Numbers] -> [ListOf String]
;Compiles a [ListOf String] and [ListOf Numbers] into a single [ListOf String]
;los and lon are equal in length
(define (combine los lon)
  (cond
    [(or (empty? los)
         (empty? lon))
     empty]
    [else
     (let [(a (first los))
           (b (number->string (first lon)))]
       (cons (string-append a b)
             (combine (rest los) (rest lon))))]))
(check-expect (combine (explode "ABC") (list 1 2 3))
              (list "A1" "B2" "C3"))

; rle-encode: DNA -> rle
;Produces the rle equivalent of the given DNA
(define (rle-encode dna)
  (local
    [(define (strings los)
       (cond
         [(empty? los) ""]
         [(empty? (rest los)) (first los)]
         [else
          (string-append (first los)
                         (strings (rest los)))]))
     ]
    (let [(a (combine (remove-dups (explode dna))
                      (count-list (explode dna))))]
      (strings a))))    
(check-expect (rle-encode "AAGCCCCTTAAAAAAAAAA") "A2G1C4T2A10")
(check-expect (rle-encode "") "")

;=====================================EX 6================================
;multiply-helper: String -> Number
;produces a number with the given string
(define (multiply-helper s)
  (cond
    [(string=? s "") 1]
    [else
     (string->number s)]))
(check-expect (multiply-helper "55") 55)
(check-expect (multiply-helper "") 1)
;multiply: [ListOf String] String String -> String
;with the given rle-encode, produce the String
;current -> stores the current letter String
;acc -> Stores the current numeric String
(define (multiply los current acc)
  (cond
    [(empty? los) (replicate (string->number acc) current)]
    [(string-numeric? (first los)) (multiply (rest los)
                                         current
                                         (string-append acc
                                                       (first los)))]
    [(not (string-numeric?(first los))) (string-append (replicate (string->number acc) current)
                                                       (multiply (rest los)
                                                                 (first los)
                                                                  ""))]
    ))
(define list1 (explode "A5B10"))
(check-expect (multiply (rest list1) (first list1) "")
              "AAAAABBBBBBBBBB")

;rle-decode: rle-encode -> DNA
;Given the rle-encode version of DNA, returns the DNA
(define (rle-decode rle-encode)
  (let [(l (explode rle-encode))]
  (if (string=? "" rle-encode)
      ""
    (multiply (rest l) (first l) ""))))
(check-expect (rle-decode "A2G1C4T2A10") "AAGCCCCTTAAAAAAAAAA")
(check-expect (rle-decode "") "")

;=======================================EX 7=========================================
(define very-effecient "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGAAAAA")
(define very-inefficient "A")
;compression-ratio : String -> Number
; returns how many times shorter rle-encode makes the given string
(check-expect (compression-ratio "AAAA") 2)
(check-expect (compression-ratio "AA") 1)
(define (compression-ratio s)
  (/ (string-length s) (string-length (rle-encode s))))

;======================================EX 8============================================
(require racket/list)

(define list2 (list 4 4 5 5 6))
(define list3 (list 6 2 3 4 5 1))
;length-3? : [ListOf Numbers] -> Number
;Produces the number of elements that split the [ListOf Numbers] into 3 equivalent pieces
(define (length-3? lon)
  (floor (/ (length lon) 3)))
(check-expect (length-3? list2) 1)
(check-expect (length-3? list3) 2)

;quick-sort-2 : [ListOf Numbers] -> [ListOf Numbers]
;produces an organized [ListOf Numbers] using 2 pivots
```
