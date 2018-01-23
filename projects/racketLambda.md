## Lambda and Complex Functions
Examples of multiple function with lambda usage. Includes a reading, overdraw checkbook, profit calculator
,no-repeats, binary tree, and map route function.

```
;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname hw13) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;Warm Up;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;===========================Ex 0================================
;(define (add-to-each n l)
;  (cond
;    [(empty? l) '()]
;    [else (cons (+ (first l) n) (add-to-each n (rest l)))]))

(define (add-to-each n l)
  (map (lambda (x) (+ x n))
       l))
(check-expect (add-to-each 10 (list 10 20 30))
              (list 20 30 40))
(check-expect (add-to-each 10 empty)
              empty)

;;;;;;;;;;;;;;;;;;;;;;;;;;Accumulating down list of Numbers;;;;;;;;;;
;===========================EX 1================================
;sums : [ListOf Numbers] Number -> Boolean
;produces boolean with respect to the current value
(define (sums lon current)
  (cond
    [(< current 0) true]
    [(empty? lon) false]
    [else
     (sums (rest lon) (+ (first lon) current))]))
(check-expect (sums (list 1 -2 1) 1) false)
(check-expect (sums (list -2 1) 1) true)
;overdraw? : [ListOf Numbers] -> Boolean
;Returns True if chronical addition is ever negative
(define (overdraw? lon)
  (sums (rest lon) (first lon)))
(check-expect (overdraw? (list 20 100 -80)) false)
(check-expect (overdraw? (list 20 -80 100)) true)
(check-expect (overdraw? (list 100 -80 20)) false)

;==============================Ex 2============================
;read : [ListOf Number] Number -> Number
;Creates the given value from the list of numbers
(define (read lon current)
  (cond
    [(empty? lon) current]
    [else
     (let [(digit-place (expt 10 (- (length lon) 1)))]
       (read (rest lon) (+ current (* digit-place (first lon)))))]))

;digits->number : [ListOf Number] -> Number
;Produces a number made up of each element in the [ListOf Number]
(define (digits->number lon)
  (read lon 0))
(check-expect (digits->number (list 2 1 1)) 211)
(check-expect (digits->number (list 2 0 1 7)) 2017)
(check-expect (digits->number (list 0 0 7)) 7)
(check-expect (digits->number empty) 0)

;===============================Ex 3=========================
;; A RadioShow is (make-rs String Number [ListOf Ad])
(define-struct rs (name minutes ads))
 
;; An Ad is (make-ad String Number Number)
(define-struct ad (name minutes profit))
 
;; Examples of data:
 
(define ipod-ad (make-ad "ipod" 2 100))
(define ms-ad (make-ad "ms" 1 500))
(define xbox-ad (make-ad "xbox" 2 300))
 
(define news-ads (list ipod-ad ms-ad ipod-ad xbox-ad))
(define game-ads (list ipod-ad ms-ad ipod-ad ms-ad xbox-ad ipod-ad))
(define bad-ads (list ipod-ad ms-ad ms-ad ipod-ad xbox-ad ipod-ad))
 
(define news (make-rs "news" 60 news-ads))
(define game (make-rs "game" 120 game-ads))

;; total-time: [ListOf Ad] -> Number
;; compute the total time for all ads in the given list
(define (total-time-acc adlist current)
  (cond [(empty? adlist) current]
        [(cons? adlist) (total-time-acc (rest adlist)
                                        (+ (ad-minutes (first adlist))
                                           current))]))
(check-expect (total-time-acc news-ads 0) 7)
(check-expect (total-time-acc game-ads 0) 10)

;========================EX 4==================================
;profit-helper : [ListOf Ad] Number -> Number
;Produces the total profit from the given [ListOf Ad]
(define (profit-helper load current)
  (cond
    [(empty? load) current]
    [else
     (profit-helper (rest load) (+ (ad-profit (first load))
                                   current))]))
(check-expect (profit-helper game-ads 0) 1600)
;total-profit : RadioShow -> Number
;Produces the total ad revenue from the given RadioShow
(define (total-profit rs)
  (profit-helper (rs-ads rs) 0))
(check-expect (total-profit game) 1600)

;==========================Ex 5====================================
;repeat? : [ListOf Ads] String -> Boolean
;checks to see if consecutive ad-name of given list
(define (repeat? load name)
  (cond
    [(empty? load) true]
    [else
     (let [(i (ad-name (first load)))]
       (if (string=? i name)
           false
           (repeat? (rest load) i)))]))
(check-expect (repeat? (rest news-ads) (ad-name(first news-ads))) true)
(check-expect (repeat? (rest bad-ads) (ad-name (first bad-ads))) false)
;no-repeat : [ListOf Ads] -> Boolean
;Produces true if acceptable list of ads
(define (no-repeat load)
  (repeat? (rest load) (ad-name (first load))))
(check-expect (no-repeat news-ads) true)
(check-expect (no-repeat bad-ads) false)

;==========================Ex 6====================================
;You don't need an accumulator to write no-repeat because we are comparing
;only two things and then getting rid of the first item. There is no
;need to "accumulate" or store multiple pieces of data with this question
(define (no-repeat-1 load)
  (cond
    [(empty? (rest load)) true]
    [else
     (let [(i (ad-name (first load)))
           (j (ad-name (first (rest load))))]
       (if (string=? i j)
           false
           (no-repeat-1 (rest load))))]))
(check-expect (no-repeat-1 news-ads) true)
(check-expect (no-repeat-1 bad-ads) false)

;=============================Ex 7====================================
; A BinaryTree is one of:
; - (make-leaf Number)
; - (make-node Number BinaryTree BinaryTree)
(define-struct leaf [n])
(define-struct node [n l r])
 
(define bt1 (make-node 3 (make-leaf 4) (make-leaf 5)))
(check-expect (path-sum bt1)
              (make-node 3 (make-leaf 7) (make-leaf 8)))
 
(define bt2 (make-node 100 bt1 (make-leaf 10)))
(check-expect (path-sum bt2)
              (make-node 100 (make-node 103 (make-leaf 107) (make-leaf 108))
                         (make-leaf 110)))
;bt-sums : BinaryTree Number -> BinaryTree
;Produces a new BinaryTree by replacing each number by the sum of its path
(define (bt-sums bt current)
  (cond
    [(leaf? bt) (make-leaf (+ (leaf-n bt)
                              current))]
    [(node? bt)
     (let [(add (+ (node-n bt)
                   current))]
       (make-node add
                  (bt-sums (node-l bt)
                           add)
                  (bt-sums (node-r bt)
                           add)))]))
;path-sum : BiaryTree -> BinaryTree
;Produces a new BinaryTree as above, bt-sums
(define (path-sum bt)
  (bt-sums bt 0))

;===========================Ex 8 ==============================
; A RailForest is a [ListOf RailTree]
; A RailTree is (make-tree Number String RailForest)
(define-struct tree [distance station forest])
 
(define atlanta-five-points
  (list (make-tree 7 "Airport" empty)
        (make-tree 9 "Indian Creek" empty)
        (make-tree 6 "Lindbergh Center"
          (list (make-tree 5 "North Springs" empty)
                (make-tree 4 "Doraville" empty)))
        (make-tree 3 "Ashby"
          (list (make-tree 2 "H.E. Holmes" empty)
                (make-tree 1 "Bankhead" empty)))))
 
(define kaohsiung-main
  (list (make-tree 14 "Gangshan South" empty)
        (make-tree 1 "Formosa"
          (list (make-tree 3 "Sizihwan" empty)
                (make-tree 8 "Siaogang" empty)
                (make-tree 10 "Daliao" empty)))))


;forest-rel->abs : RailForest -> RailForest
;Produces the absolute distance of locatinos from the starting point

;=============================Ex 9=================================
; A Map is [ListOf Road]
; A Road is (make-road String String PositiveNumber)
(define-struct road (start end dist))
; interpretation: the dist of a road is a number of miles
;possible? : Map String String -> Boolean
;returns true if a possible route in given Map
;(define (possible? map start end)
;  (cond
;    [(empty? map) false]
;    [(cons? map)
;     (if (string=? start (road-start (first map))))
;         
;distance : Map String String -> Number
;produces the number of miles between two locations
```
