import java.sql.ResultSet;
import java.sql.SQLException;

public class ShapeEnManager {
	private Shape[] oldShape;

	public void addShapeDatebase(Shape shape) {
		try {
			// STEP 4: Execute a query
			String sql = "INSERT INTO `paint`.`shape_tb` (`type`, `color`, `startX`, `startY`, `curX`, `curY`, `userID`) VALUES ('"
					+ shape.getTypeShape()
					+ "', '"
					+ shape.getColor()
					+ "', '"
					+ shape.getStartX()
					+ "', '"
					+ shape.getStartY()
					+ "', '"
					+ shape.getCurX()
					+ "', '"
					+ shape.getCurY()
					+ "', '"
					+ shape.getUserID() + "');";
			Connect.stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// Handle errors for JDBC
			// se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
		} finally {
			// finally block used to close resources
		}
	}

	public Shape[] addDataShape(Person person) {
		try {
			int userID = person.getUserID();
			String sql;
			sql = "SELECT * FROM shape_tb where `userID` = '" + userID + "';";
			ResultSet rs = Connect.stmt.executeQuery(sql);
			int i = 0;
			
			while(rs.next()){
				i++;
			}
			
			oldShape = new Shape[i];
			int j = 0;
			
			while(rs.previous()){
				oldShape[j] = new Shape();
				oldShape[j].setTypeShape(rs.getString("type"));
				oldShape[j].setColor(rs.getString("color"));
				oldShape[j].setStartX(rs.getInt("startX"));
				oldShape[j].setStartY(rs.getInt("startY"));
				oldShape[j].setCurX(rs.getInt("curX"));
				oldShape[j].setCurY(rs.getInt("curY"));
				j += 1;
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			// massage = "Database error";
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			// massage = "Database error";
		} finally {

		}
		return oldShape;
	}
}
