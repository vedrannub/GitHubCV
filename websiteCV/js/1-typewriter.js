function typewrite (target, text, loop, speed) {
  // (A) SET DEFAULT OPTIONS
  target = document.getElementById(target);
  if (speed === undefined) { speed = 200; }
  if (loop === undefined) { loop = false; }
  
  // (B) DRAW TYPEWRITER
  let pointer = 0;
  let timer = setInterval(function(){
    pointer++;
    if (pointer <= text.length) {
      target.innerHTML = text.substring(0, pointer);
    } else {
      if (loop) { pointer = 0; }
      else { clearInterval(timer); }
    }
  }, speed);
}