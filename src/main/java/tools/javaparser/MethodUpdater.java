package tools.javaparser;

import com.github.javaparser.ASTHelper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodUpdater extends VoidVisitorAdapter<String> {

	@Override
	public void visit(MethodDeclaration n, String arg) {
		String methodName = n.getName();
		if (methodName.equals("test1")) {
			n.setName(methodName + arg);
			Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "newParameter");
			ASTHelper.addParameter(n, newArg);
		}
	}
}
