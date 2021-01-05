import java.awt.*;
import java.util.*;
/*
THIS IS THE MAIN CLASS CONTAINING THE INFORMATION RELATED TO THE GRAPH
THE LEADING PRINCIPLE I WILL BE USING WILL BE ADJACENCY MATRIX CONTAINING THE LIST OF EDGES AND STUFF
THIS WILL DETERMINE WHETHER THE GRAPH IS FULLY COLOURED AND ALSO IN THE BEST WAY POSSIBLE
 */
public class Graph {
    /**
     * so im gonna be settng a highlight colour and a standard colour, because that's what I have an idea of(FOR EDGES)
     * i think labelling of edges and vertices starts at 0
     */
    public static final Color highlightColor=Color.BLACK;//setting the highlight color as BLACK
    public static final Color standardColor=Color.RED;//setting standard as RED
    public static Color[] AllColors=new Color[]{Color.GREEN,Color.BLUE,Color.GRAY,Color.WHITE,Color.YELLOW,Color.PINK,Color.DARK_GRAY};
    /*
    now i have to create some arrays for vertices and variables for the colours of edges
     */
    private int[][] neighbours;//contains the list of neighbours for that particular edge
    private Vertex[] listVertices;
    private Color edgesColor;//the one from standard
    private Color edgeHighlightColor;
    private int[][] adjacentMatrix;
    private int usedColor;
    private int[] coloring;
    /**
    now we have to initialise the graph with vertices and edges
    parameters- the length of neighbours will be equal to vertices length(idk about this read it on some site but i understand the logic)
     */
    public Graph(Vertex[] vertice,int[][] neighbours){
        this.listVertices=vertice;
        this.edgesColor=standardColor;
        this.edgeHighlightColor=highlightColor;
        this.neighbours=neighbours;
        adjacentMatrix=getAdjacentMatrix();
        coloring=new int[neighbours.length];//setting it in the constructors itself
        usedColor=1;
    }
    /**
     * calculates the available colors for a given vertex
     * @return available colors
     * we assume that available colors=usedColours and this goes up by incrementing the count i think
     */
    public int[] availableColors(int vertice){
        int[] availableColors=new int[usedColor];//starting with this, i think
        availableColors[0]=usedColor;
        int count=1;//starting the count of available colors
        for(int i=0;i<usedColor;i++){
            boolean used=false;
            for(int j=0;j<adjacentMatrix.length;j++){
                if(adjacentMatrix[vertice][j]==1 && vertexColor(j)==i){
                    used=true;
                    break;
                }
            }
            if(!used){//when loop is not executed as far as i understand
                availableColors[count]=i;
                count++;
            }
        }
        return availableColors;
    }
    public int vertexColor(int v){
        return coloring[v];
    }
    public void setVColor(int v,int color){
        if(color>usedColor){
            usedColor=color+1;//making sure that used colours is  more
        }
        coloring[v]=color;
    }
    private int[][] getAdjacentMatrix(){
        if(adjacentMatrix==null){
            //create another adjacent matrix with neighbours length
            int[][] newAdjacent=new int[neighbours.length][neighbours.length];
            for(int i=0;i< neighbours.length;i++){
                for(int neigh:neighbours[i]){
                    newAdjacent[neigh][i]=1;
                    newAdjacent[i][neigh]=1;
                }
            }
            return newAdjacent;
        }
        else{
            return adjacentMatrix;
        }

    }
    public Vertex retrieveVertex(int v){
        return listVertices[v];
    }


}
