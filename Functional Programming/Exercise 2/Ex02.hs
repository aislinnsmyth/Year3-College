{- butrfeld Andrew Butterfield -}
module Ex02 where

name, idno, username :: String
name      =  "Addison-Smyth, Aislinn"  -- replace with your name
idno      =  "19337226"    -- replace with your student id
username  =  "addisona"   -- replace with your TCD username


declaration -- do not modify this
 = unlines
     [ ""
     , "@@@ This exercise is all my own work."
     , "@@@ Signed: " ++ name
     , "@@@ "++idno++" "++username
     ]

-- Datatypes and key functions -----------------------------------------------

-- do not change anything in this section !

type Id = String

data Expr
  = Val Double
  | Add Expr Expr
  | Mul Expr Expr
  | Sub Expr Expr
  | Dvd Expr Expr
  | Var Id
  | Def Id Expr Expr
  deriving (Eq, Show)

type Dict k d  =  [(k,d)]

define :: Dict k d -> k -> d -> Dict k d
define d s v = (s,v):d

find :: Dict String d -> String -> Either String d
find []             name              =  Left ("undefined var "++name)
find ( (s,v) : ds ) name | name == s  =  Right v
                         | otherwise  =  find ds name

type EDict = Dict String Double

v42 = Val 42 ; j42 = Just v42

-- do not change anything above !

-- Part 1 : Evaluating Expressions -- (50 test marks, worth 25 Exercise Marks) -

-- Implement the following function so all 'eval' tests pass.

-- eval should return `Left msg` if:
  -- (1) a divide by zero operation was going to be performed;
  -- (2) the expression contains a variable not in the dictionary.
  -- see test outcomes for the precise format of those messages

eval :: EDict -> Expr -> Either String Double
eval d (Var e) = find d e 
eval _ (Val e) = Right e

eval d (Add x y) = case (eval d x, eval d y) 
  of
    (Right a, Right b) -> Right (a+b)     --  Add evaluating to a+b
    (Right a,Left b) -> Left b
    (Left a, Right b) -> Left a
    (Left a, Left b) -> Left a
eval d (Mul x y) = case (eval d x, eval d y)  
  of
    (Right a, Right b) -> Right (a*b)      -- Mul evaluating to a*b
    (Right a,Left b) -> Left b
    (Left a, Right b) -> Left a
    (Left a, Left b) -> Left a
eval d (Sub x y) = case( eval d x, eval d y)
  of
    (Right a, Right b) -> Right (a-b)      -- Sub evaluating to a-b
    (Right a,Left b) -> Left b
    (Left a, Right b) -> Left a
    (Left a, Left b) -> Left a
eval d (Dvd x y) = case (eval d x, eval d y)
  of
    (Right a, Right b) ->               --  Dvd evaluating to a/b unless b==0 then div by zero msg
      if b==0 then Left "div by zero"    -- return left msg 
      else Right (a/b)
    (Right a,Left b) -> Left b
    (Left a, Right b) -> Left a
    (Left a, Left b) -> Left a
eval d (Def z x y) = case(eval d x)
  of
    Right a -> eval (define d z a) y
    _ -> Left "div by zero"             --return left msg
-- Part 1 : Expression Laws -- (15 test marks, worth 15 Exercise Marks) --------

{-

There are many, many laws of algebra that apply to our expressions, e.g.,

  x + y         =   y + z            Law 1
  (x + y) + z   =   x + (y + z)      Law 2
  (x - y) - z   =   x - (y + z)      Law 3
  x*x - y*y     =   (x + y)*(x - y)  Law 4
  ...

  We can implement these directly in Haskell using Expr

  Function LawN takes an expression:
    If it matches the "shape" of the law lefthand-side,
    it replaces it with the corresponding righthand "shape".
    If it does not match, it returns Nothing

    Implement Laws 1 through 4 above
-}


law1 :: Expr -> Maybe Expr
law1 e = case (e)
  of
    (Add a b) -> Just(Add b a)    -- x+y = y+z
    _ -> Nothing                  --Does not match returns nothing

law2 :: Expr -> Maybe Expr
law2 e = case (e)
  of
    Add(Add a b) c -> Just(Add a (Add b c)) --(x+y)+z = x+(y+z)
    _ -> Nothing                  --Does not match returns nothing

law3 :: Expr -> Maybe Expr
law3 e = case (e)
  of
    Sub (Sub a b) c -> Just(Sub a (Add b c))  --(x-y)-z = x-(y+z)
    _ -> Nothing                  --Does not match returns nothing

law4 :: Expr -> Maybe Expr
law4 e = case (e)
  of
 -- (Sub (Mul a b) (Mul c d)) -> (Mul (Add a c ) (Sub b d))
   -- where a == b (&&) b == d
    _ -> Nothing                  --Does not match returns nothing
