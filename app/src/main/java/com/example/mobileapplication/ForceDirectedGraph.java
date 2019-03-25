package com.example.mobileapplication;

import java.util.ArrayList;
import java.util.Random;

public class ForceDirectedGraph {
    private static final double SPEED_DIVISOR = 32;
    private static final double AREA_MULTIPLICATOR = 400;
    private ArrayList<Node> graph;
    public ArrayList<comOfficer> officername;
    private float area;
    private double gravity;
    private double speed;
    private double maxDisplace;
    private double kFactor;
    private int nIterations;



    public ForceDirectedGraph() {
        super();
        init();
    }


    public ForceDirectedGraph(ArrayList<Node> graph) {
        super();
        this.graph = graph;
        init();
    }

    public void init() {
        generateComplexGraph();

        nIterations = 100;

        speed = 1;
        area = 400;
        gravity = 10;
        officername = new ArrayList<>();
        maxDisplace = (Math.sqrt(AREA_MULTIPLICATOR * area) / (double) 10.0);
        kFactor = Math.sqrt((AREA_MULTIPLICATOR * area) / (double) (1.0 + graph.size()));
    }

    public void forcedBasedDrawing() {
        if (nIterations < 1 || graph.size() < 1)
            return;

        for (int i = 0; i < graph.size(); i++) {
            Node nodeV = graph.get(i);
            nodeV.setDispX(0);
            nodeV.setDispY(0);
        }

        for (int r = 0; r < graph.size(); r++) {
            Node nodeV = graph.get(r);
            for (int j = 0; j < graph.size(); j++) {
                if (r != j) {
                    Node nodeU = graph.get(j);
                    double deltaPosX = nodeV.getPosX() - nodeU.getPosX();
                    double deltaPosY = nodeV.getPosY() - nodeU.getPosY();
                    double magnitudeDelta = vectorMagnitude(deltaPosX, deltaPosY);
                    if (magnitudeDelta > 0) {
                        double rForce = forceRepulsive(magnitudeDelta);
                        nodeV.setDispX(nodeV.getDispX() + deltaPosX / magnitudeDelta * rForce);
                        nodeV.setDispY(nodeV.getDispY() + deltaPosY / magnitudeDelta * rForce);
                    }
                }
            }
        }


        for (int q = 0; q < graph.size(); q++) {
            Node newNodeV = graph.get(q);

            for (int k = 0; k < newNodeV.getAdjacentNodes().size(); k++) {
                Node nodeU = newNodeV.getAdjacentNodes().get(k);

                double deltaPosX = newNodeV.getPosX() - nodeU.getPosX();
                double deltaPosY = newNodeV.getPosY() - nodeU.getPosY();
                double magnitudeDelta = vectorMagnitude(deltaPosX, deltaPosY);

                if (magnitudeDelta > 0) {
                    double aForce = forceAttraction(magnitudeDelta);
                    newNodeV.setDispX(newNodeV.getDispX() - deltaPosX / magnitudeDelta * aForce);
                    newNodeV.setDispY(newNodeV.getDispY() - deltaPosY / magnitudeDelta * aForce);
                    nodeU.setDispX(nodeU.getDispX() + deltaPosX / magnitudeDelta * aForce);
                    nodeU.setDispY(nodeU.getDispY() + deltaPosY / magnitudeDelta * aForce);
                }
            }
        }


        for (int d = 0; d < graph.size(); d++) {
            Node newNodeV = graph.get(d);
            double magnitudeDelta = vectorMagnitude(newNodeV.getDispX(), newNodeV.getDispY());

            if (magnitudeDelta > 0) {
                double gf = 0.01f * kFactor * (double) gravity * d;
                newNodeV.setDispX(newNodeV.getDispX() - gf * newNodeV.getPosX() / magnitudeDelta);
                newNodeV.setDispY(newNodeV.getDispY() - gf * newNodeV.getPosY() / magnitudeDelta);
            }
        }


        for (int u = 0; u < graph.size(); u++) {
            Node newNodeV = graph.get(u);
            newNodeV.setDispX(newNodeV.getDispX() * speed / SPEED_DIVISOR);
            newNodeV.setDispY(newNodeV.getDispY() * speed / SPEED_DIVISOR);
        }


        for (int p = 0; p < graph.size(); p++) {
            Node newNodeV = graph.get(p);
            double magnitudeDelta = vectorMagnitude(newNodeV.getDispX(), newNodeV.getDispY());

            if (magnitudeDelta > 0) {
                double limitedDist = Math.min(magnitudeDelta, maxDisplace * ((double) speed / SPEED_DIVISOR));

                if (!newNodeV.isDragged()) {
                    newNodeV.setPosX(newNodeV.getPosX() + newNodeV.getDispX() * limitedDist );
                    newNodeV.setPosY(newNodeV.getPosY() + newNodeV.getDispY() * limitedDist );
                }
            }

        }
    }

    private double forceAttraction(double x) {
        return (x * x) / kFactor;
    }

    private double forceRepulsive(double x) {
        return kFactor * kFactor / x;
    }

    private double vectorMagnitude(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * @return the graph
     */
    public ArrayList<Node> getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(ArrayList<Node> graph)
    {
        this.graph = graph;
    }

    public void generateComplexGraph() {
        graph = new ArrayList<Node>();
        Random rand = new Random();
        if (officername != null) {
            for (int i = 0; i < officername.size(); i++) {
                Node node = new Node(i,officername.get(i).getOfficerName());
                node.setPosX((double) rand.nextInt(400));
                node.setPosY((double) rand.nextInt(400));
                graph.add(node);
            }

            if (officername.size() > 0) {
                Node node = graph.get(0);
                ArrayList<Node> adjacentNodes = new ArrayList<Node>();
                for (int i = 1; i < officername.size()-1; i++){
                    adjacentNodes.add(graph.get(i));
                }
                node.setAdjacentNodes(adjacentNodes);
            }
        }





//        adjacentNodes.add(graph.get(4));
//        adjacentNodes.add(graph.get(5));



//        Node node1 = graph.get(1);
//        ArrayList<Node> adjacentNodes1 = new ArrayList<Node>();
//        adjacentNodes1.add(graph.get(6));
//        adjacentNodes1.add(graph.get(7));
//        node1.setAdjacentNodes(adjacentNodes1);
//
//
//        Node node2 = graph.get(2);
//        ArrayList<Node> adjacentNodes2 = new ArrayList<Node>();
//        adjacentNodes2.add(graph.get(8));
//        adjacentNodes2.add(graph.get(9));
//        node2.setAdjacentNodes(adjacentNodes2);
//
//
//        Node node3 = graph.get(3);
//        ArrayList<Node> adjacentNodes3 = new ArrayList<Node>();
//        adjacentNodes3.add(graph.get(10));
////        adjacentNodes3.add(graph.get(11));
////        adjacentNodes3.add(graph.get(12));
////        adjacentNodes3.add(graph.get(13));
////        adjacentNodes3.add(graph.get(14));
////        adjacentNodes3.add(graph.get(15));
////        adjacentNodes3.add(graph.get(16));
//        node3.setAdjacentNodes(adjacentNodes3);
    }

    public void setOfficername(ArrayList<comOfficer> officername) {
        this.officername = officername;
        //generateComplexGraph();
    }
}
