// Mary Huibregtse
// CSE 143
// HW 8
// TA Shanti Camper Singh
// Class HuffmanNode constructs and manages nodes

import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode> {
   public int character; //integer value of character
   public int frequency; // frequency of character
   public HuffmanNode left; // reference to left subtree
   public HuffmanNode right; // reference to right subtree
   
   // Post: constructs leaf node with given character and 
   //       frequency
   public HuffmanNode(int character, int frequency) {
      this(character, frequency, null, null);
   }
   
   //Post: constructs node with zero frequency and zero
   //      character values 
   public HuffmanNode() {
      this(0,0,null,null);
   }
   
   // Post: constructs branch node with given character and
   //       frequency and links
   public HuffmanNode(int character, int frequency, 
            HuffmanNode left, HuffmanNode right) { 
      this.character = character; 
      this.frequency = frequency; 
      this.left = left; 
      this.right = right;
   }
   
   // returns comparable 
   public int compareTo(HuffmanNode other) {
      return (this.frequency - other.frequency);
   }
}