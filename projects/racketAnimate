## Introduction to Animation
Simulating an animation through Racket Big-Bang function. 
```
;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname HW5) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require 2htdp/image)
(require 2htdp/universe)

;EX 1
;A Pair is struct for Two Numbers
;(make-pair Number Number)
(define-struct pair [x y])

(define pair1 (make-pair 50 50))
(define pair2 (make-pair 3 4))

;EX 2
;A Pairs is one of:
; - (make-no-pairs)
; - (make-some-pairs Pair Pairs)
(define-struct no-pairs [])
(define-struct some-pairs [p ps])

;examples pairs
(define pairs1 (make-some-pairs pair1 (make-no-pairs)))
(define pairs2 (make-no-pairs))


;EX 3
;(define (process-pair p)
;  (cond
;    [(xpostion? p) ...]
;    [(yposition? p) ...]))

(define (pos-pair p pos)
  (cond
    [(string=? pos "x") (pair-x p)]
    [(string=? pos "y") (pair-y p)]))

(check-expect (pos-pair pair2 "x") 3)

(define (process-pairs ps)
  (cond
    [(no-pairs? ps) (...)]
    [(some-pairs? ps) (...(some-pairs-p ps)...(process-pairs (some-pairs-sp ps))...)]))

;EX 4
;a draw-pairs is a function that takes in a Pairs as location (x,y) on scene
;draw-pairs : Pairs -> Image
(define c
  (circle 10 "solid" "red"))
(define SCN-W 500)
(define SCN-H 500)
(define CANVAS (empty-scene SCN-W SCN-H))

(define (draw-pairs ps)
  (cond
    [(no-pairs? ps) CANVAS]
    [(some-pairs? ps) (place-image (circle 10 "solid" "red")
                       (pos-pair (some-pairs-p ps) "x") (pos-pair (some-pairs-p ps) "y")
                       (draw-pairs (some-pairs-ps ps)))]))

(check-expect (draw-pairs pairs1)
              (place-image (circle 10 "solid" "red")
                           50 50
                           CANVAS))
;EX 5
; A any-paint is a function that returns a Pairs
; any-paint : Pairs Number Number MouseEvent -> Pairs
; create a new Pairs with x- and y-coordinates of mouse
(define (any-paint ps x y me)
  (cond
    [(mouse-event? me) (make-some-pairs (make-pair x y) ps)]
    [else ps]))

; A any-undo is a function that returns a Pairs
; any-undo : Pairs KeyEvent -> Pairs
; remove the most recent Pairs
(define (any-undo ps ke)
  (cond
    [(key-event? ke) (make-no-pairs)]
    [else ps]))
; A run-any is af unction that runs big-bang
; run-any: Pairs -> World
;runs big-bang with initial world of Pairs
(define (run-any ps)
  (big-bang ps
            [to-draw draw-pairs]
            [on-mouse any-paint]
            [on-key any-undo]))

;EX 6
;a Paint is an on-mouse function
; Paint: Pairs Number Number MouseEvent -> World
; creates Pairs dependent on "button-down" and "drag"

(define (paint ps x y me)
  (cond
    [(or (mouse=? me "button-down") (mouse=? me "drag")) (make-some-pairs (make-pair x y) ps)]
    [else ps]))

;a Undo is a on-key function
; Undo : Pairs KeyEvent -> World
; deletes the painted circles on the scene
(define (undo ps ke)
  (cond
    [(key=? ke "z") (make-no-pairs)]
    [else ps]))

;a Run is a function of big-bang with Pairs as initial World
; Run : Pairs -> World
;runs big-bang
(define (run ps)
  (big-bang ps
            [to-draw draw-pairs]
            [on-mouse paint]
            [on-key undo]))
                           

(run-any pairs1)
(run pairs1)

;EX 7
; A SolarObject is one of:
; - a sun
; - a planet
(define-struct sun [size])
(define-struct planet [distance nextSO])

(define sun1 (make-sun 5))
(define planet1 (make-planet 50 sun1))
(define planet2 (make-planet 100 planet1))


;EX 8
(define (process-solar-object so)
  (cond
    [(sun? so) (...(make-sun-size so)...)]
    [(planet? so) (...(make-planet-distance so)...(make-planet-nextSO so)...)]))

;EX 9
; a distance-of-solar-object is function that returns a Number
; distance-of-solar-object : SolarObject -> Number
;calculate distance of furthest planet to sun(center)

(define (distance-of-solar-object so)
  (cond
    [(sun? so) 0]
    [(planet? so) (+ (planet-distance so) (distance-of-solar-object (planet-nextSO so)))]))

(check-expect (distance-of-solar-object planet2) 150)
(check-expect (distance-of-solar-object planet1) 50)
(check-expect (distance-of-solar-object sun1) 0)

;EX 10
;a add-to-solar-object is function that creates new SolarObject
; add-to-solar-object : SolarObject Number -> SolarObject
; adds new planet at given distance to a SolarObject
(define (add-to-solar-object SO distance)
  (cond
    [(sun? SO) SO]
    [(planet? SO)
     (make-planet distance
                  (make-planet (planet-distance SO)
                               (add-to-solar-object (planet-nextSO SO) (planet-distance SO))))]))

(check-expect (add-to-solar-object planet1 100)
              (make-planet 100 (make-planet 50 (make-sun 5))))

;EX 11
; a draw-solar-object is a function that creates an image
; draw-solar-object : SolarObject Number -> Image
; draw images of SolarObjects at given distances
(define suns (overlay (circle 20 "solid" "orange") CANVAS))

(define (draw-solar-object so)
  (cond
    [(sun? so) suns]
    [(planet? so) (overlay
                   (circle (distance-of-solar-object so) "outline" "green")
                   (draw-solar-object (planet-nextSO so)))]))

(check-expect (draw-solar-object planet1)
              (overlay
               (circle 50 "outline" "green")
               (circle 20 "solid" "orange")
               CANVAS))
```
