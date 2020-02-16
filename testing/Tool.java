public class Tool extends ToolBelt{

  public String tool(String tool){
    if ( tool.equalsIgnoreCase("hammer")) {
      return "a hammer is a tool";
    }
    else if (tool.equalsIgnoreCase("drill")) {
      return "a drill is a tool";
    }
    else{
      return ("you\'re an idiot theres no such thing as a "+ tool);
    }
  }

}
//tirel
