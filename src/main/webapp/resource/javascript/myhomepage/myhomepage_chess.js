function beginchess() {
    var board,
        game = new Chess();

    var thebestnextmove;
    var thedepth=3;

    var minimaxRootBlackComputer =function(depth, game, isMaximisingPlayer,alph,beta) {
        if(depth==0){
            return -evaluateBoard(game.board());//Calculating chessboard status
        }
        var newGameMoves = game.moves();
        if (isMaximisingPlayer) {
            var bestMove = -9999;
            for (var i = 0; i < newGameMoves.length; i++) {
                game.move(newGameMoves[i]);
                var mybebestMove=minimaxRootBlackComputer(depth - 1, game, !isMaximisingPlayer,alph,beta);
                if(mybebestMove>bestMove){
                    if(thedepth==depth){
                        thebestnextmove=newGameMoves[i];//store the best move (the first floor)
                    }
                    bestMove=mybebestMove;
                }
                game.undo();
                alph = Math.max(alph, bestMove);
                if (beta <= alph) {
                    return bestMove;
                }
            }
            return bestMove;
        } else {
            var bestMove = 9999;
            for (var i = 0; i < newGameMoves.length; i++) {
                game.move(newGameMoves[i]);
                bestMove = Math.min(bestMove, minimaxRootBlackComputer(depth - 1, game, !isMaximisingPlayer,alph,beta));
                game.undo();
                beta = Math.min(beta, bestMove);
                if (beta <= alph) {
                    return bestMove;
                }
            }
            return bestMove;
        }
    };

    //Calculating chessboard status
    var evaluateBoard = function (board) {
        var totalEvaluation = 0;
        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                totalEvaluation = totalEvaluation + getPieceValue(board[i][j], i ,j);
            }
        }
        return totalEvaluation;
    };

    var getPieceValue = function (piece, x, y) {
        if (piece === null) {
            return 0;
        }
        var getAbsoluteValue = function (piece, isWhite, x ,y) {
            if (piece.type === 'p') {
                return 10 + ( isWhite ? pawnEvalWhite[y][x] : pawnEvalBlack[y][x] );
            } else if (piece.type === 'r') {
                return 50 + ( isWhite ? rookEvalWhite[y][x] : rookEvalBlack[y][x] );
            } else if (piece.type === 'n') {
                return 30 + knightEval[y][x];
            } else if (piece.type === 'b') {
                return 30 + ( isWhite ? bishopEvalWhite[y][x] : bishopEvalBlack[y][x] );
            } else if (piece.type === 'q') {
                return 90 + evalQueen[y][x];
            } else if (piece.type === 'k') {
                return 900 + ( isWhite ? kingEvalWhite[y][x] : kingEvalBlack[y][x] );
            }
            throw "Unknown piece type: " + piece.type;
        };

        var absoluteValue = getAbsoluteValue(piece, piece.color === 'w', x ,y);
        return piece.color === 'w' ? absoluteValue : -absoluteValue;
    };

    var computerdothemove=function () {
        //check is over before game move
        if (game.game_over()) {
            alert('Game over');
        }
        var bestMove = getBestMove(game);
        //game go next
        game.move(thebestnextmove);
        //board position fresh
        board.position(game.fen());
        //check is over after game move
        if (game.game_over()) {
            alert('Game over');
        }
    };

    var getBestMove=function (game) {
        var depth=thedepth;
        var bestMove = minimaxRootBlackComputer(depth, game, true,-10000,10000);
        return bestMove;
    };

    var removeGreySquares = function() {
        $('#board .square-55d63').css('background', '');
    };

    var greySquare = function(square) {
        var squareEl = $('#board .square-' + square);

        var background = '#a9a9a9';
        if (squareEl.hasClass('black-3c85d') === true) {
            background = '#696969';
        }

        squareEl.css('background', background);
    };

    var onDragStart = function(source, piece) {
        // // do not pick up pieces if the game is over
        // // or if it's not that side's turn
        // if (game.game_over() === true ||
        //     (game.turn() === 'w' && piece.search(/^b/) !== -1) ||
        //     (game.turn() === 'b' && piece.search(/^w/) !== -1)) {
        //     return false;
        // }

        //white player ,black computer
        if (game.in_checkmate() === true || game.in_draw() === true ||
            piece.search(/^b/) !== -1) {
            return false;
        }
    };

    var onDrop = function(source, target) {
        removeGreySquares();

        // see if the move is legal
        var move = game.move({
            from: source,
            to: target,
            promotion: 'q' // NOTE: always promote to a queen for example simplicity
        });

        // illegal move
        if (move === null) return 'snapback';

        //computer go
        window.setTimeout(computerdothemove, 250);
    };

    var onMouseoverSquare = function(square, piece) {
        // get list of possible moves for this square
        var moves = game.moves({
            square: square,
            verbose: true
        });

        // exit if there are no moves available for this square
        if (moves.length === 0) return;

        // highlight the square they moused over
        greySquare(square);

        // highlight the possible squares for this piece
        for (var i = 0; i < moves.length; i++) {
            greySquare(moves[i].to);
        }
    };

    var onMouseoutSquare = function(square, piece) {
        removeGreySquares();
    };

    var onSnapEnd = function() {
        board.position(game.fen());
    };

    var cfg = {
        pieceTheme: 'resource/chessboardjs-0.3.0/img/chesspieces/wikipedia/{piece}.png',
        draggable: true,
        position: 'start',
        onDragStart: onDragStart,
        onDrop: onDrop,
        onMouseoutSquare: onMouseoutSquare,
        onMouseoverSquare: onMouseoverSquare,
        onSnapEnd: onSnapEnd
    };
    board = ChessBoard('board', cfg);
}

// 'resource/chessboardjs-0.3.0/img/chesspieces/wikipedia/{piece}.png'


var reverseArray = function(array) {
    return array.slice().reverse();
};

var pawnEvalWhite =
    [
        [0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0],
        [5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0],
        [1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0],
        [0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5],
        [0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0,  0.0],
        [0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5],
        [0.5,  1.0, 1.0,  -2.0, -2.0,  1.0,  1.0,  0.5],
        [0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0]
    ];

var pawnEvalBlack = reverseArray(pawnEvalWhite);

var knightEval =
    [
        [-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0],
        [-4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0],
        [-3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0],
        [-3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0],
        [-3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0],
        [-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0],
        [-4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0],
        [-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0]
    ];

var bishopEvalWhite = [
    [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0],
    [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0],
    [ -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0],
    [ -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0],
    [ -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0],
    [ -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0],
    [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0]
];

var bishopEvalBlack = reverseArray(bishopEvalWhite);

var rookEvalWhite = [
    [  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0],
    [  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0]
];

var rookEvalBlack = reverseArray(rookEvalWhite);

var evalQueen = [
    [ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0],
    [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0],
    [ -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5],
    [  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5],
    [ -1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0],
    [ -1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0]
];

var kingEvalWhite = [

    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0],
    [ -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0],
    [  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 ],
    [  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 ]
];

var kingEvalBlack = reverseArray(kingEvalWhite);

function homepageChess() {
    $("#homepagepanel").html("<div id=\"board\" style=\"width: 600px\"></div>");
    beginchess();
}
