grammar RoverInput;

input
   : size roverData*
   ;

size
   : INTEGER SPACE INTEGER WS? NEWLINE
   ;

roverData
   : start WS? NEWLINE commands WS? NEWLINE
   ;

start
   : INTEGER SPACE INTEGER SPACE DIRECTION
   ;

commands
   : COMMAND+
   ;

COMMAND
   : [LRM]
   ;

DIRECTION
   : [NESW]
   ;

INTEGER
   : [+\-]? INT
   ;

// no leading zeros
fragment INT
   : '0' | [1-9] [0-9]*
   ;

SPACE
   : ' '
   ;

WS
   : [ \t] + -> skip
   ;

NEWLINE
   : [\r\n]+
   ;
