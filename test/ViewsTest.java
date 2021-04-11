public class ViewsTest {

  //tests:
  // Readable - AnimationReader:
  ////1. Null readable
  ////2. Empty readable
  //// readable with " " but no other text
  ////3. wrong readable format non-.txt
  ////4. Unexpected keywords in readable !"canvas" !"shape" !"motion"
  ////5. Expected words
  ////// missing "canvas"
  ////// missing "shape"
  ////// missing "motion"
  ////// various capitalization for expected words
  ////6. Canvas
  ////// Missing canvas values
  ////7. Shape
  ////// -Missing shape name
  ////// -Missing shape type "rectangle" or "ellipse"
  ////// -various capitalization for shape types
  ////8. Field values
  //////#                  start                           end
  //////#        --------------------------    ----------------------------
  //////#        t  x   y   w  h   r   g  b    t   x   y   w  h   r   g  b
  //////motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0
  ////// -Missing field values
  ////// -unexpected field values "(*&#*"
  ////// -empty field values
  ////   -Illegal field values?


  //4. For SVG and View: test different speeds


}
