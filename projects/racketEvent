## Key Events
Introduction to Key Events and conditional statements. 
```
;-------------PART I: BIG BANG----------------
;Ex 1
; A draw is a function that creates a String
; draw : String Number String -> image
;(define (draw word) ...)
    ; a word is a string
(define (draw word)
  (text word 100 "red"))

;Ex 2
;A press is a function that creates an image using draw
; press: KeyEvent -> image
; A KeyEvent is one of the following:
; "s"
; "a"
; "m"
;(define (press a-key) ...)
(define (press w a-key)
  (cond
    [(key=? a-key "s") (string-append w "Sam")]
    [(key=? a-key "a") (string-append w "Amazing")]
    [(key=? a-key "m") (string-append w "Majestic")]
    [(key=? a-key " ") ""]
    [else w]))  

(big-bang "Amazing" 
          [to-draw draw]
          [on-key press])

;--------------PART II: STRUCTURES-----------------
;Ex 4
(define-struct date (year month day))
;List the 5 functions
; make-date : Number Number Number -> Date
;(make-date...)
;
;;date-year : Date -> year
;;recall year of the given Date
;(date-year...)
;
;;date-month : Date -> month
;;recall month of the given Date
;(date-month...)
;
;;date-day : Date -> day
;;recall day of the given Date
;(date-day...)
;
;;date? : Anything -> Boolean (t or f)
;;checks if input is a Date
;(date?...)

;Ex 5

;An address is a struct comprised of the following:
; - house number (Number)
; - street name (String)
; - city (String)
; - state (String)
(define-struct address (hn sn c s))

;Ex 6
;(define (process-address x)
;  (cond [(address? x) (...)]
;        [else "Not Address"]))

;Ex 7
;process-address is a function that checks if the house number is even
;process-address : (make-address hn sn c s) -> Boolean
(define (process-address x)
  (cond [(address? x) (even? (address-hn x))]
        [else "Not Address"]))

(check-expect (process-address (make-address 350 "beaver" "bloomington" "IN")) #true)
(check-expect (process-address (make-address 351 "beaver" "bloomington" "IN")) #false)          
(check-expect (process-address "apple") "Not Address")

;Ex 8
;smaller-address is a function that returns the address with the smaller house number
;smaller-address : (make-address hn sn c s) (make-address hn sn c s) -> (make-address hn sn c s)


(define (smaller-address a1 a2)
  (cond [(< (address-hn a1) (address-hn a2)) a1]
        [(< (address-hn a2) (address-hn a1)) a2]))

;test
(check-expect (smaller-address (make-address 350 "beaver" "bloomington" "IN") (make-address 351 "beaver" "bloomington" "IN"))
              (make-address 350 "beaver" "bloomington" "IN"))
(check-expect (smaller-address (make-address 352 "beaver" "bloomington" "IN") (make-address 351 "beaver" "bloomington" "IN"))
              (make-address 351 "beaver" "bloomington" "IN"))

;ex 9
; mail is a function that produces a string
; mail : Address -> String
;(define (mail am)...)
(define (mail am)
  (above (beside (text (number->string (address-hn am)) 20 "black") (text (address-sn am) 20 "black"))
         (beside (text (address-c am) 20 "black") (text (address-s am) 20 "black"))))

;test
(check-expect (mail (make-address 350 "Beaver" "Bloomington" "IN"))
              (above (beside
                            (text "350" 20 "black") (text "Beaver" 20 "black"))
                     (beside
                            (text "Bloomington" 20 "black") (text "IN" 20 "black"))))
                            
;--------------PART III :  BOOK EXERCISES-------------
;Ex 83
;an Editor is a structure:
; (make-editor String String)
;(make-editor s t) describes an editor whose visible text is
;(string-append s t) with cursor between s and t
(define-struct editor [pre post])

;render is a function which takes an Editor and produces an image
;render : Editor(struct) -> image
(define (render s)
  (overlay/align "middle" "middle"
            (rectangle 1 20 "solid" "red")
            (text (string-append (editor-pre s) (editor-post s)) 16 "black")
         (empty-scene 200 20)))


;Ex 84
;edit is a function that takes two inputs:
;- Editor, ed
;- KeyEvent, ke
;edit: ed ke -> editor
;(define (edit ed ke)
;  (cond [(key=? ke "left") ...(editor-pre ed)...]
;        [(key=? ke "right") ...(editor-pre ed)...]
;        [(key=? ke "\b") ...(editor-pre ed)...]
;        [else ed]))

