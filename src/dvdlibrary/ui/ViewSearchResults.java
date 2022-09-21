package dvdlibrary.ui;

import java.util.Map;

import io.UserIO;

public class ViewSearchResults extends ViewImpl {

    protected Map<Integer, String> dvdList;

    public ViewSearchResults(UserIO io, Map<Integer, String> dvdList) {
        super(io);
        this.dvdList = dvdList;
    }

    @Override
    public String getTitle() {
        return "Search Results";
    }

    @Override
    public void render() {

        super.render();

        io.print("");

        for (int key : dvdList.keySet())
            io.print(key + ": " + dvdList.get(key));
    }
}
