// Mary Huibregtse
// CSE 143
// HW 8
// TA Shanti Camper Singh
// Class HuffmanTree manages Huffman coding
// to compress files 

import java.util.*;
import java.io.*;

public class HuffmanTree {
   private HuffmanNode overallRoot; // HuffmanTree reference
   
   // post: constructs huffman tree given frequencies and 
   //       corresponding character values
   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> leafs = new PriorityQueue<HuffmanNode>();
      int size = count.length;
      for(int i = 0; i < size; i ++) {
         if(count[i] > 0 ) {
            leafs.add(new HuffmanNode(i, count[i]));
         }         
      }
      leafs.add(new HuffmanNode(size, 1));
      overallRoot = buildTree(leafs);
   }
   
   // post: constructs huffman tree given frequencies and 
   //       corresponding character values and node
   private HuffmanNode buildTree(Queue<HuffmanNode> leafs) {
      if (leafs.size() > 1) {
         HuffmanNode left = leafs.remove();
         HuffmanNode right = leafs.remove();
         HuffmanNode root = new HuffmanNode(0, left.frequency + 
                  right.frequency, left, right);
         leafs.add(root);
         return buildTree(leafs); 
      } else {
         return leafs.remove();
      }  
   }
   
   // pre: file contains tree stored in standard from
   // post: reconstructs a Huffman tree from a given 
   //       file. 
   public HuffmanTree(Scanner input) {
      while(input.hasNext()) {
        int character = Integer.parseInt(input.nextLine());
        String code = input.nextLine();
        overallRoot = readTree(code, character, overallRoot);
           
      }
   }
   
   // pre: file contains tree stored in standard from
   // post: reconstructs a Huffman tree from a given 
   //       file, character, and node. 
   private HuffmanNode readTreeRightNow(String code, int character, 
            HuffmanNode root) {
      if (root == null) {
         root = new HuffmanNode();
      }
      if(code.length()== 0) {
         root.character = character;    
      } else {
         String newCode = "";
         if(code.length() > 1) {
            newCode = code.substring(1);
         }
         if (code.charAt(0) == '0') {
            root.left = readTree(newCode, character, 
               root.left);             
         }else {
            root.right = readTree(newCode, character, 
               root.right);
         }
      }
      return root;  
   } 
   
   // post: given an output stream, prints Huffman Tree in standard
   //       format
   public void write(PrintStream output) {
      String path = "";
      writeHelper(output, overallRoot, path);
   }
   
   // post: given an output stream, HuffmanNode, and tree path, 
   //       prints a HuffmanNode in standard format 
   private void writeHelper(PrintStream output, HuffmanNode root, 
            String path) {
      if(root != null) {
         if(root.left == null) {
            output.println(root.character);  
            output.println(path); 
         }else {
            writeHelper(output, root.left, path + "0");
            writeHelper(output, root.right, path + "1");
         }
      }
   }
   
   // pre: input stream contains legal encoding of characters for
   //      tree, has ending eof
   // post: given a BitInput stream, output stream, and end of file 
   // marker, writes corresponding characters to the output
   public void decode(BitInputStream input, PrintStream output, 
            int eof) {
      int value = input.readBit();
      HuffmanNode root = overallRoot;
      while(value != eof) {
         if(root.left == null) {
            if(root.character == eof) {
               value = eof;
            } else {
               output.write(root.character);
               root = overallRoot;
            }
         }else {
            if(value == 0) {
               root = root.left;
            } else {
               root = root.right;               
            }
            value = input.readBit(); 
         } 
      }
   }
}