import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * A class containing static utility methods to assist with Coursework 1.
 *
 * @author Filippos Pantekis
 */
public class CourseworkUtilities {
    private static Class<?> cta;
    private static Method cgnxt;
    private static Method cgnam;
    private static final String[] DOT_COLOURS = {"tomato", "green", "gray", "orange", "powderblue", "indianred", "lemonchiffon",
            "mediumpurple", "sandybrown", "teal", "red", "steelblue", "thistle"};

    /**
     * A method that, given a valid File, reads its contents and parses it into a linked datastructure of TouristAttraction objects. The
     * TouristAttraction class is <b>not</b> supplied with this given code. Before using this method you must ensure that you have
     * created a TouristAttraction class in accordance with the spec!
     * <br>
     * <b>Note:</b> This method is designed to compile and live alongside your code, even if you haven't yet created a TouristAttraction
     * class. To call this class you must first create the TouristAttraction class, and then call the method as:
     * <pre>
     * TouristAttraction blah = CourseworkUtilities.loadTouristAttractions(someFileParameter);
     * </pre>
     * The variable name 'blah' can be any valid variable name. The parameter 'someFileParameter' must be a File you have created
     * somewhere in your code before you call this method :-)
     *
     * @param toRead The input file containing TouristAttraction map data.
     * @return This method will always return an instance of a TouristAttraction, which is the starting attraction in the map loaded from
     * file.
     */
    @SuppressWarnings("unchecked")
    public static <TouristAttraction> TouristAttraction loadTouristAttractions(File toRead) {
        //Note: The below code is intentionally complex, and does not comply with all code conventions taught in class.
        //You should not consider this code guidance for the quality of your code. This code is not intended to be read.
        if (toRead == null) {
            throw new IllegalArgumentException("The File argument given is null. Cannot read a null file!");
        }
        if (!(toRead.exists() && toRead.isFile() && toRead.canRead())) {
            throw new IllegalArgumentException("The File argument given (" + toRead + ") does not exist, is not readable, or is not a " +
                    "file!");
        }
        Class<?> ta = getTA();
        Constructor<?> tac = roe(() -> ta.getDeclaredConstructor(String.class), "Your TouristAttraction class doesn't offer a way to be " +
                "constructed using the name as a parameter. Check the task requirements and make relevant changes.");
        chAcc(tac, "The constructor for TouristAttraction should be publicly accessible.");
        Method taan = roe(() -> ta.getDeclaredMethod("addNext", ta), "Your TouristAttraction class doesn't contain the method addNext " +
                "that takes a TouristAttraction.");
        chAcc(taan, "The method addNext in TouristAttraction should be publicly accessible.");

        List<String> lns;
        try {
            lns = Files.readAllLines(toRead.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file " + toRead, e);
        }
        if (lns.isEmpty() || !lns.contains("")) {
            throw new IllegalStateException("The file (" + toRead + ") is malformed. Ensure this is the right file, and that its contents" +
                    " are correctly structured. You can re-download the file from Canvas and try again.");
        }
        AtomicReference<RuntimeException> thrown = new AtomicReference<>();
        List<?> tas = lns.stream().limit(lns.indexOf("")).map(l -> {
            try {
                return roe(() -> tac.newInstance(l), "Failed to create an instance of TouristAttraction using the constructor that takes " +
                        "a String, when given the parameter text: '" + l + "'. This could be because of an exception (error) thrown from " +
                        "within your constructor (see error below).");
            } catch (RuntimeException e) {
                if (thrown.get() != null) {
                    thrown.set(e);
                }
                return null;
            }
        }).collect(Collectors.toList());

        if (thrown.get() != null) {
            throw thrown.get();
        }

        Set<String> un = new HashSet<>();
        for (Object o : tas) {
            String n = getName(o);
            if (n == null) {
                throw new IllegalStateException("getName on a TouristAttraction returned null, despite the object having been constructed" +
                        " with a non-null name. This indicates some issue in the TouristAttraction class.");
            }
            un.add(n);
        }
        if (un.size() != tas.size()) {
            throw new IllegalStateException("The file appears malformed, or the getName method does not return the correct value.");
        }

        try {
            lns.stream().skip(lns.indexOf("") + 1).forEach(a -> {
                int i = Integer.parseInt(a.substring(0, a.indexOf(':')));
                if (a.endsWith(":")) {
                    return;
                }
                List<Integer> c =
                        Arrays.stream((a).substring(a.indexOf(':') + 1).split(",")).map(Integer::parseInt).collect(Collectors.toList());
                if (tas.size() <= i || c.stream().anyMatch(j -> j == i || tas.size() <= j)) {
                    throw new IllegalArgumentException("The input file appears to be corrupted. There's a missmatch of indexes to " +
                            "TouristAttractions. Re-download from Canvas and try again.");
                }
                Object n = tas.get(i);
                c.forEach(x -> roe(() -> taan.invoke(n, tas.get(x)), "Failed to call addNext() on an instance of TouristAttraction. This " +
                        "could be due to an exception thrown from your addNext() method."));
            });
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse file. See attached error", e);
        }
        return (TouristAttraction) tas.get(0);
    }

    /**
     * Converts the given linked datastructure of TouristAttraction objects into DOT 'code' which can then be pasted
     * into and visualised by tools such as: <a href="https://dreampuf.github.io/GraphvizOnline/">https://dreampuf.github.io/GraphvizOnline/</a>
     * <br>
     * <b>Note:</b> The linked datastructure accessible from the input TouristAttraction must not contain cycles.
     * <br>
     * This method can be called with a TouristAttraction parameter that is not nessesarily the start of the map.
     *
     * @param attr The TouristAttraction object from where to start. This must not be null.
     * @return A DOT representation of the datastructure, which can be visualised.
     */
    public static String convertToDotGraph(Object attr) {
        if (attr == null) {
            throw new IllegalArgumentException("You have passed a null object in the parameter of this method. A null TouristAttraction " +
                    "cannot be printed!");
        }
        if (!attr.getClass().equals(getTA())) {
            throw new IllegalArgumentException("The convertToDorGraph method may only be given a TouristAttraction parameter. " +
                    "Double-check that you are calling the method with the right parameter type.");
        }
        Stack<SiblingLine> lns = new Stack<>();
        Set<Object> v = new HashSet<>(Arrays.asList(new String[]{null}));
        lns.add(new SiblingLine());
        l(attr, lns, v);
        StringBuilder sb = new StringBuilder("digraph TouristAttractions {\n");
        int n = -1;
        for (SiblingLine l : lns) {
            String c = DOT_COLOURS[n = (n + 1) % DOT_COLOURS.length];
            sb.append('\t').append("node[style=filled,color=").append(c).append("]").append('\n').append('\t');
            if (l.i != null)
                sb.append(fm(getName(l.i))).append("->");
            sb.append(l.l.stream().map(a -> fm(getName(a)))
                    .collect(Collectors.joining("->"))).append('\n');
        }
        return sb.append('}').toString();
    }

    private static void l(Object l_, Stack<SiblingLine> l, Set<Object> _l) {
        if (_l.contains(l_) && l_ != null) {
            throw new IllegalStateException("Your TouristAttraction linked datastructure contains one or more loops (i.e., from a given " +
                    "TouristAttraction, it is possible to move to it's successor TouristAttraction(s) and eventually return back to it). " +
                    "For this assignment, we assume this will never be the case (i.e, this is out of spec). Double-check your linking of " +
                    "TouristAttraction objects or the integrity of the file you are reading (if that is not the supplied file).");
        }
        if (!_l.contains(l_)) {
            l.peek().//
                    l.add(l_);
            _l.add(l_);
            if (getNext(l_).isEmpty()) {
                return;
            }
            Object i = getNext(l_).get(0);
            l(i, l, _l);
            for (Object i_ : getNext(l_).subList(1, getNext(l_).size())) {
                SiblingLine _i = new SiblingLine();
                _i.i = l_;
                l.add(_i);
                l(i_, l, _l);
            }
        }
    }

    private static List<?> getNext(Object o) {
        if (o == null) {
            return null;
        }
        Object res = roe(() -> getNX().invoke(o), "Failed to call getNext on an instance of TouristAttraction. This is likely because your getNext method does not meet the specification, or because it threw an exception while running. See error below for more.");
        if (res == null) {
            throw new IllegalStateException("Your getNext method has returned null for a TouristAttraction with a non-null name.");
        }
        return (List<?>) res;
    }

    private static String getName(Object o) {
        if (o == null) {
            return null;
        }
        Object res = roe(() -> getNA().invoke(o), "Failed to call getName on an instance of TouristAttraction. This is likely because your getName method does not meet the specification, or because it threw an exception while running. See error below for more.");
        if (res == null) {
            throw new IllegalStateException("Your getName method has returned null for a TouristAttraction with a non-null name.");
        }
        return (String) res;
    }

    private static class SiblingLine {
        Object i;
        List<Object> l = new ArrayList<>();
    }

    private static String fm(String s) {
        return "\"" + s + "\"";
    }

    private static Method getNA() {
        if (cgnam != null) {
            return cgnam;
        }
        Method tagn = roe(() -> getTA().getDeclaredMethod("getName"), "Your TouristAttraction class doesn't contain the method getName that " +
                "returns the name of the TouristAttraction.");
        chAcc(tagn, "The method getName in TouristAttraction should be publicly accessible.");
        if ((tagn.getModifiers() & STATIC) != 0) {
            throw new IllegalStateException("The getName method in TouristAttraction is a non-instance method. Make sure it is an instance method, implemented in line with the spec!");
        }
        if (!String.class.isAssignableFrom(tagn.getReturnType())) {
            throw new IllegalStateException("Your getName method returns something other than a String!");
        }
        return cgnam = tagn;
    }

    private static Method getNX() {
        if (cgnxt != null) {
            return cgnxt;
        }
        Method tagnx = roe(() -> getTA().getDeclaredMethod("getNext"), "Your TouristAttraction class doesn't contain the method getNext that " +
                "returns the name of the TouristAttraction.");
        chAcc(tagnx, "The method getNext in TouristAttraction should be publicly accessible.");
        if ((tagnx.getModifiers() & STATIC) != 0) {
            throw new IllegalStateException("The getNext method in TouristAttraction is a non-instance method. Make sure it is an instance method, implemented in line with the spec!");
        }
        Type t = tagnx.getGenericReturnType();
        if (!(t instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Your getNext method should return a list of TouristAttraction objects. Check your method signature.");
        }
        ParameterizedType pt = (ParameterizedType) t;
        Type raw = pt.getRawType();
        if (!(raw instanceof Class<?>) || !List.class.isAssignableFrom(((Class<?>) raw))) {
            throw new IllegalArgumentException("Your getNext method should return a list of TouristAttraction objects. Check your method signature.");
        }
        Type arg = pt.getActualTypeArguments()[0];
        if (!arg.equals(getTA())) {
            throw new IllegalStateException("The returned list of getNext must contain elements of type TouristAttraction. Check your method signature.");
        }
        return cgnxt = tagnx;
    }

    private static Class<?> getTA() {
        if (cta != null) {
            return cta;
        }
        Class<?> ta = roe(() -> Class.forName("TouristAttraction"), "There doesn't seem to be a TouristAttraction class in your project. " +
                "Double-check you have created the class, in the default package, and named it correctly.");
        if ((ta.getModifiers() & (ABSTRACT | INTERFACE)) != 0 || !isPublic(ta.getModifiers())) {
            throw new IllegalStateException("Your TouristAttraction class must be a public, non-abstract class.");
        }
        cta = ta;
        return ta;
    }

    private static void chAcc(Executable m, String msg) {
        if (!isPublic(m.getModifiers())) {
            throw new IllegalStateException(msg);
        }
    }

    private static <T> T roe(Callable<T> c, String m) {
        try {
            return c.call();
        } catch (Exception e) {
            throw new IllegalStateException(m, e);
        }
    }
}
