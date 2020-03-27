package cs3500.animator.view;

/**
 * a factory class for the views of this program
 */
public class ViewCreator {
  /**
   * create a view according to given viewtype
   *
   * @param viewType given viewtype
   * @return a view as the result
   */
  public static View create(ViewType viewType) {
    switch (viewType){
      case SVG:
        return
      case VISUAL:
      case TEXTUAL:
      default:
    }
  }

  /**
   * an enum classs for representing view typr for this project.
   */
  public enum ViewType {
    TEXTUAL,
    VISUAL,
    SVG;

    /**
     * find the view type accoring to the given string.
     *
     * @param s given string
     * @return ViewType as the result
     * @throws IllegalArgumentException if can identify the given string
     */
    public static ViewType findViewType(String s) {
      switch (s) {
        case "text":
        case "Text":
          return TEXTUAL;
        case "visual":
        case "Visual":
          return VISUAL;
        case "svg":
        case "Svg":
          return SVG;
        default:
          throw new IllegalArgumentException("can't identify input");
      }
    }
  }

}
