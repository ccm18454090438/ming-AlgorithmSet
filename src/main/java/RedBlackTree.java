
public class RedBlackTree {

  node Head;


  public RedBlackTree() {//A constructor
    Head = new node();
    Head.isNil = true;
  }

  private static node getNIL(){//Get a NIL node
    node a = new node();
    a.isNil = true;
    a.color = 2;
    return a;
  }




  public void chageNode(int Old, int New){// Modification method
    deletNode(Old);
    addNode(New);

  }


  public int checkNode(int n){//Query method, with this number, returns 0, otherwise returns-1
    if (getNode(n)!=null){
      return 0;
    }else{
      return -1;
    }
  }


  public void Put() {//Output method
    PUT(Head);
    System.out.println();
  }

  private void PUT(node head) {//Specific output method
    if (head == null) {//In the sequence traversal
      return;
    }
    if (head.isNil == false) {
      PUT(head.nextLeft);
      System.out.print(head.getValue()+"  ");//A few Spaces in between
      PUT(head.nextRight);
    } else {
      return;
    }
  }


  public int addNode(int n) {//Add methods
    return addTwo(n);
    //Failure returns -1
  }

  private int addTwo(int n) {//Add a concrete implementation of the method
    //Find the insert node
    node to;
    to = Head;
    node parent = null;//Initialize the parent node to be null
    while (to.isNil != true) {
      parent = to;//You need to bind the parent node

      if (n > to.value) {
        to = to.nextRight;
      } else {

        if (n == to.value) {
          return -1;//Returns -1 if this number exists
        } else {
          to = to.nextLeft;
        }
      }
    }
    //The parent node has been found
    //Insert and bind the parent node
    to.clone(new node(n));
    to.setParent(parent);//Binding parent node
    //The node is now found and the insert is complete
    addFixup(parent, to);//Check balance to maintain balance
    return -1;
  }


  private int addFixup(node parent, node n) {//Added method to maintain balance
    //Judge each situation
    if (n.getValue() == Head.getValue()) {//Add nodes as head nodes
      Head.setColor(2);
      Head.setParent(null);//Head node condition
      return 0;
    }

    if (parent.getColor() == node.BLACK) {//The parent node is black and ends directly without adjustment
      return 0;
    }

    if (parent.getColor() == node.RED) {
      //When the father is red, we need the uncle node.
      //Get the uncle node and grandfather node first
      node gp = parent.getParent();//Get grandfather node
      node uncle;//Uncle node

      if (parent == gp.getNextLeft()) {//Get Uncle Node
        uncle = gp.getNextRight();
      } else {
        uncle = gp.getNextLeft();
      }
      //The node has been acquired to judge the situation.
      //Determined that the father is red
      if (uncle.getColor() == node.RED) {
        //Paternal red uncle red condition
        //Father Hong, uncle Hong, father and uncle are both black, and their grandfather is black for the new N.
        uncle.setBlack();
        parent.setBlack();
        gp.setRed();
        return addFixup(gp.getParent(), gp);//Leveling up
      } else {
        //Paternal red uncle black condition
        //Judge the direction
        if (gp.getNextLeft() == parent && n == parent.getNextLeft()) {
          // PN Same as left
          parent.setBlack();
          gp.setRed();//Change color first and then rotate to prevent the relationship from getting wrong after rotation.
          node i = new node();
          i.clone(parent.getNextRight());//Why do you do this temporarily? please refer to the clone method in node.
          parent.getNextRight().clone(gp);
          parent.getNextRight().setNextLeft(i);
          i.setParent(parent.getNextRight());
          gp.clone(parent);
          return 0;//balance
        }

        if (parent.getNextRight() == n && gp.getNextRight() == parent) {
          //PN Same as right
          parent.setBlack();
          gp.setRed();
          node i = new node();
          i.clone(parent.getNextLeft());//Save the node
          parent.getNextLeft().clone(gp);
          parent.getNextLeft().setNextRight(i);
          i.setParent(parent.getNextLeft());
          gp.clone(parent);
          return 0;//Rotation completed and leveled successfully.
        }

        if (gp.getNextLeft() == parent && parent.getNextRight() == n) {
          //P left N right
          //First, turn p to the left.
          n.getNextLeft().clone(parent);
          n.getNextLeft().setNextRight(getNIL());
          parent.clone(n);
          //Complete the left turn and now PN is the same as the left.
          parent.setBlack();
          gp.setRed();//Change color first and then rotate to prevent the relationship from getting wrong after rotation.
          node i = new node();
          i.clone(parent.getNextRight());//Keep it for the time being, brother.
          parent.getNextRight().clone(gp);
          parent.getNextRight().setNextLeft(i);
          i.setParent(parent.getNextRight());
          gp.clone(parent);
          return 0;
          //Right-hand rotation complete. Leveling complete.
        }

        if (gp.getNextRight() == parent && parent.getNextLeft() == n) {
          //P right N left
          n.getNextRight().clone(parent);
          n.getNextRight().setNextLeft(getNIL());
          parent.clone(n);
          //Complete the right-hand rotation and now PN is the same as the right.
          parent.setBlack();
          gp.setRed();
          node i = new node();
          i.clone(parent.getNextLeft());//Temporary preservation
          parent.getNextLeft().clone(gp);
          parent.getNextLeft().setNextRight(i);
          i.setParent(parent.getNextLeft());
          gp.clone(parent);
          //Complete left-handed rotation
          return 0;
        }
        //The addition situation has been considered.
      }
    }
    return -1;
  }


  public node getNode(int n) {//Get node
    //Find Node
    if (Head.getValue() == n) {
      return Head;
    }

    node to = Head;
    while (to.isNil == false && to.getValue() != n) {
      if (to.getValue() > n) {
        to = to.nextLeft;
      } else {
        to = to.nextRight;
      }
    }
    if (to.getValue()==n) {//Whether the search was successful or not
      return to;
    } else {
      return null;
    }
  }


  public int deletNode(int x) {
    //Delete nod
    // Get the node first, that is, the node to be deleted
    node n = getNode(x);
    return deletNodeTwo(n);//Delete nod

  }


  private int deletNodeTwo(node n) {
    //It is not necessary to delete a node to determine whether it exists or not.
    if (n == null) {
      //It doesn't exist.
      return -1;
    }
    if (n.getNextRight()==null){//Prevent null pointers from appearing
      n.nextRight=getNIL();
    }

    if (n.nextLeft==null){
      n.nextLeft=getNIL();
    }
    //It is not empty now and it is a normal node.
    //Deal with the case without child nodes first
    if (n.getNextLeft().isNil && n.getNextRight().isNil) {
      //No child node
      //To delete as red
      if (n.getColor() == node.RED) {
        n.setValue(-1);
        n.isNil = true;//Red no child node is deleted directly.
        n.setBlack();
        return 0;//There is no need to maintain balance if the deletion is successful.
      } else {
        //Black no child node
        //Directly delete the dimension level of the successor NIl node
        n.isNil = true;
        n.setValue(-1);
        //Maintain balance with n nodes as unbalanced points
        return deletFixup(n);
      }
    }


    //Now there is a child node.
    if (  (n.getNextRight().isNil && n.getNextLeft().isNil == false) ||
        (n.getNextRight().isNil==false && n.getNextLeft().isNil)  ){
      //Get child nodes
      node c;
      if (n.getNextLeft().isNil==false){
        c=n.getNextLeft();
      }else {
        c=n.getNextRight();
      }
      //Child nodes have been obtained
      //Judge in the light of each situation
      if (n.getColor()==node.RED){
        n.clone(c);
        return 0;
        //N is red c must be black direct replacement
      }else {
        //N is black
        if (c.getColor()==node.RED){
          //If n is black, it is red.
          n.clone(c);
          n.setBlack();
          return 0;
          //Replace discoloration
        }else {
          //N is sunspot and black is black.
          n.clone(c);//Replacement leveling
          return deletFixup(n);
        }

      }
    }
    //The deletion of two child nodes is now handled.
    if (n.getNextLeft().isNil==false && n.getNextRight().isNil==false){
      //Find the successor node and then delete the successor node
      node max=getMax(n);
      //Get successor node
      n.setValue(max.value);//Replace
      //Delete
      return deletNodeTwo(max);
      //Balance
    }
    return -1;
  }

  private int deletFixup(node n){
    //The parameter is the node to be balanced.
    if (n==Head){
      //There is no need to operate when the balance node is the root node.
      return 0;
    }

    //Non-root node
    //The subsequent operation is to get the relevant nodes.
    node S,SL,SR,U,GP,Parent;
    Parent=n.getParent();
    if (n==Parent.getNextLeft()){
      S=Parent.getNextRight();
    }else {
      S=Parent.getNextLeft();
    }
    SL=S.getNextLeft();
    SR=S.getNextRight();
    GP=Parent.getParent();
    if (GP!=null) {
      if (Parent == GP.getNextLeft()) {
        U = GP.getNextRight();
      } else {
        U = GP.getNextLeft();
      }
    }
    //Related nodes have been obtained
    //Related situation analysis

    if (S.getColor()==node.BLACK){
      return SisBlack(Parent,S,SL,SR);
    }else {
      //Brother node is red
      //The brothers are on the left and on the right.
      if (S ==Parent.getNextLeft()) {
        //The brother node is on the left.
        S.setBlack();
        Parent.setRed();
        node i=new node();
        i.clone(S.getNextRight());
        S.getNextRight().clone(Parent);
        S.getNextRight().setNextLeft(i);
        i.setParent(S.getNextRight());
        Parent.clone(S);
        //Complete rotation
        Parent=Parent.getNextRight();
        S=Parent.getNextLeft();
        return SisBlack(Parent,S,S.getNextLeft(),S.getNextRight());
      }else {
        S.setBlack();
        Parent.setRed();
        node i=new node();
        i.clone(S.getNextLeft());
        S.getNextLeft().clone(Parent);
        S.getNextLeft().setNextRight(i);
        i.setParent(S.getNextLeft());
        Parent.clone(S);
        Parent=Parent.getNextLeft();
        S=Parent.getNextRight();
        return SisBlack(Parent,S,S.getNextLeft(),S.getNextRight());
      }

    }

  }


  private int SisBlack(node Parent,node S,node SL,node SR){
    //The sibling nodes are black
    if (SL==null){
      SL=getNIL();
    }

    if (SR==null){
      SR=getNIL();
    }
    if (SL.getColor()==node.BLACK && SR.getColor()==node.BLACK){
      //All sibling nodes are black
      if (Parent.getColor()==node.BLACK){
        //The parent node is black
        S.setRed();
        return deletFixup(Parent);

      }else {
        //The father of red
        Parent.setBlack();
        S.setRed();
        //The father is black and the brother is red
        return 0;
      }
    }else {
      //Not all sibling nodes are black
      //There are four cases

      if (S==Parent.getNextLeft() && SL.getColor()==node.RED){
        return SisLAndSLisR(Parent,S,SL);
      }

      if (S==Parent.getNextRight() && SR.getColor()==node.RED){
        //S is the right and the right is red
        return SisRAndSRisR(Parent,S,SR);
      }

      if (S==Parent.getNextLeft() && SL.getColor()==node.BLACK){
        //S is the left subson S is the left subson black and the right subson red
        SR.setBlack();
        S.setRed();
        //First left-handed
        node i=new node();
        i.clone(SR.getNextLeft());
        SR.getNextLeft().clone(S);
        SR.getNextLeft().setNextRight(i);
        i.setParent(SR.getNextLeft());
        S.clone(SR);
        SL=S.getNextLeft();
        //The new left child
        //Left child and left is red
        return SisLAndSLisR(Parent,S,SL);
      }

      if (S==Parent.getNextRight() && SR.getColor()==node.BLACK){
        //S is the right and the right is black and the left is red
        if (SL.getNextRight()==null){
          SL.nextRight=getNIL();
        }

        if (SL.getNextLeft()==null){
          SL.nextLeft=getNIL();
        }
        SL.setBlack();
        S.setRed();
        node i=new node();
        i.clone(SL.getNextRight());
        SL.getNextRight().clone(S);
        SL.getNextRight().setNextLeft(i);
        i.setParent(SL.getNextRight());
        S.clone(SL);
        SR=S.getNextRight();
        //The new right is red
        return SisRAndSRisR(Parent,S,SR);
      }

    }
    return -1;
  }


  private int SisLAndSLisR(node Parent,node S,node SL){
    S.setColor( Parent.getColor());
    Parent.setBlack();
    SL.setBlack();
    node i=new node();
    i.clone(S.getNextRight());
    S.getNextRight().clone(Parent);
    S.getNextRight().setNextLeft(i);
    i.setParent(S.getNextRight());
    Parent.clone(S);
    //P and S discoloration, the left hand turns black, and the right hand turns black
    return 0;
  }


  private int SisRAndSRisR(node Parent,node S,node SR){
    //S is the right and the right is red
    SR.setBlack();
    S.setColor(Parent.getColor());
    Parent.setBlack();
    node i=new node();
    i.clone(S.getNextLeft());
    S.getNextLeft().clone(Parent);
    S.getNextLeft().setNextRight(i);
    i.setParent(S.getNextLeft());
    Parent.clone(S);
    return 0;
  }


  //Find the successor node method
  private static node getMax(node a){
    //Parameter is the node to be deleted with two child nodes
    a=a.getNextLeft();
    while (a.getNextRight().isNil==false){
      a=a.getNextRight();
    }

    return a;
    //Return successor node
  }

}


class node {

  static final int RED = 1;
  static final int BLACK = 2;
  static node NIL;
  int value;
  node nextLeft;//left
  node nextRight;//right
  node parent;//parent node
  boolean isNil;
  int color;//1 is red//   2 is  black

  public node() {//The default color is red
    color = 2;
    isNil = false;
  }

  public node(int n) {
    value = n;
    color = 1;
    this.toNIL();
  }

  public static node getNIL() {
    node a = new node();
    a.isNil = true;
    a.color = 2;
    return a;
  }

  public void setRed() {
    this.color = RED;
  }

  public void setBlack() {
    this.color = BLACK;
  }

  public void clone(node a) {// Cloning methods all clones except the parent node
    if (a==null){
      this.isNil=true;
      return;
    }
    if (a.isNil)
      this.isNil=true;
    else {
      this.value = a.value;
      this.color = a.color;
      if (a.getNextLeft() == null) {
        a.nextLeft = getNIL();
      }
      if (a.getNextRight() == null) {
        a.nextRight = getNIL();
      }

      this.nextLeft = a.nextLeft;
      this.nextRight = a.nextRight;//Don't forget to modify the parent node after cloning
      a.getNextLeft().setParent(this);
      a.getNextRight().setParent(this);
      this.isNil = a.isNil;
    }
  }

  public void setNil(boolean nil) {
    isNil = nil;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public node getNextRight() {
    return nextRight;
  }

  public void setNextRight(node nextRight) {
    this.nextRight = nextRight;
  }

  public node getNextLeft() {
    return nextLeft;
  }

  public void setNextLeft(node nextLeft) {
    this.nextLeft = nextLeft;
  }

  public node getParent() {
    return parent;
  }

  public void setParent(node parent) {
    this.parent = parent;
  }

  private void toNIL() {
    if (this.getNextRight() == null) {
      this.nextRight = new node();
      this.nextRight.setBlack();
      this.getNextRight().isNil = true;
    }

    if (this.getNextLeft() == null) {
      this.nextLeft = new node();
      this.nextRight.setBlack();
      this.getNextLeft().isNil = true;

    }
  }


}

