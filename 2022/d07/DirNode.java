package d07;

import java.util.ArrayList;
import java.util.List;

public class DirNode {

    String name;
    DirNode parent;
    List<DirNode> children = new ArrayList<>();
    List<FileNode> files = new ArrayList<>();

    public DirNode() {
    }

    public DirNode(String name) {
        this.name = name;
    }

    public DirNode(DirNode parent) {
        this.name = parent.getName();
        this.parent = parent.getParent();
        this.children = parent.getChildren();
        this.files = parent.getFiles();
    }

    public DirNode(String name, DirNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public int getFilesSizeSum() {
        return files.stream().mapToInt(FileNode::getSize).sum();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DirNode getParent() {
        return parent;
    }

    public void setParent(DirNode parent) {
        this.parent = parent;
    }

    public List<DirNode> getChildren() {
        return children;
    }

    public void setChildren(List<DirNode> children) {
        this.children = children;
    }

    public List<FileNode> getFiles() {
        return files;
    }

    public void setFiles(List<FileNode> files) {
        this.files = files;
    }
}
