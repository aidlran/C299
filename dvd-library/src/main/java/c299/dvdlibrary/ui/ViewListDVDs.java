package c299.dvdlibrary.ui;

import c299.dvdlibrary.dto.DVD;
import c299.io.UserIO;

public class ViewListDVDs extends ViewImpl {

    protected DVD[] dvdList;

    public ViewListDVDs(UserIO io, DVD[] dvdList) {
        super(io);
        this.dvdList = dvdList;
    }

    @Override
    public String getTitle() {
        return "All DVDs";
    }

    @Override
    public void render() {

        super.render();

        io.print("");

        for (DVD dvd : dvdList)
            io.print(dvd.getMovieTitle());
    }
}
