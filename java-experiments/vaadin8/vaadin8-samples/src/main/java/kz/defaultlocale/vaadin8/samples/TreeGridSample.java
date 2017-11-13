package kz.defaultlocale.vaadin8.samples;

import com.vaadin.ui.TreeGrid;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TreeGridSample extends Sample {

    private final String ROOT = "D:\\Projects\\Other";

    public TreeGridSample() {
        super("Tree grid");
    }

    @Override
    protected void init() {
        TreeGrid<Project> treeGrid = new TreeGrid<>();
        treeGrid.setItems(getRootProjects(), Project::getSubProjects);
        treeGrid.addColumn(Project::getName).setCaption("Project Name");
        treeGrid.addColumn(Project::getHoursDone).setCaption("Hours Done");
        treeGrid.addColumn(Project::getLastModified).setCaption("Last Modified");
        treeGrid.setWidth("100%");
        addComponent(treeGrid);
    }

    private List<Project> getRootProjects() {
        File root = new File(ROOT);
        Project rootProject = new Project(root);
        process(rootProject, root);
        ArrayList<Project> list = new ArrayList<>();
        list.add(rootProject);
        return list;
    }

    private void process(Project project, File file) {
        for (File childFile : file.listFiles()) {
            Project childProject = new Project(childFile);
            if (childFile.isDirectory()) {
                process(childProject, childFile);
            }
            project.addChild(childProject);
        }
    }

    private static class Project {

        private final String name;
        private final int hoursDone;
        private final Date lastModified;
        private final ArrayList<Project> children;

        public Project(String name, Date lastModified) {
            this.name = name;
            this.lastModified = lastModified;
            hoursDone = (new Random()).nextInt(100);
            children = new ArrayList<>();
        }

        private Project(File file) {
            this(file.getName(), new Date(file.lastModified()));
        }

        public List<Project> getSubProjects() {
            return children;
        }

        public String getName() {
            return name;
        }

        public int getHoursDone() {
            return hoursDone;
        }

        public Date getLastModified() {
            return lastModified;
        }

        private void addChild(Project childProject) {
            children.add(childProject);
        }
    }

}
