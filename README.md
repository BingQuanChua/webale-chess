# webale-chess

OOAD Assignment

<h5>
    Esther
</h5>

Arrow
ArrowForwardMovement
ArrowBackwardMovement

<h5>
	HanYi    
</h5>

Chevron
ChevronMovement

<h5>	
    MinXuan
</h5>

Sun
SunMovement

<h5>
    BingQuan
</h5>

Triangle
TriangleMovement

<h5>
    ChiaXuan
</h5>

Plus
PlusMovement
(Need design)
HomeFrame
GameBoard + ChessBoard + ToolBar
**design not yet finished

<h5>
    Done (?)
</h5>

/ Piece
/ Movement
/ ChessTile
/ Coordinate

<h5>
    To be done
</h5>
Controller (pass Game, HomeFrame, GameBoard)
Game (save and load)
rotate icon



<h2>
    Save File Format
</h2>

##########################

  WEBALE CHESS SAVE FILE

##########################

Turn: Red

Move Count: 0

##########################

Blue Plus 0 0

Blue Triangle 1 0

:

:

Blue Arrow Forward 0 1

:

:

##########################

// scan through the board row by row, top to bottom

// record pieces that are present