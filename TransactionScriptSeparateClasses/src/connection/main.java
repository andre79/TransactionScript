package connection;

import java.util.Scanner;

public class main {
	public static void main(String[] args) throws Exception {
		Gateway dao = new Gateway();
		Scanner s = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		
		String initDate;
		String finalDate;
		int opcao = 1;
		int key;

		while (opcao != 0) {
			
			System.out.println("");

			System.out.println("###################### A��es dispon�veis: #######################");
			System.out.println("Listar Produtos                                                - 1");
			System.out.println("Listar Contratos                                               - 2");
			System.out.println("Listar Contrato/Produto                                        - 3");
			System.out.println("Listar Contrato/Produto Espec�fico por Contrato                - 4");
			System.out.println("Listar Contrato/Produto Espec�fico por Contrato e Per�odo      - 5");
			System.out.println("Calcular Lan�amento Financeiro Espec�fico por Per�odo          - 6");
			System.out.println("Calcular Lan�amento Financeiro Espec�fico por Per�odo SQLSum   - 7");
			System.out.println("Listar Lan�amentos Financeiros                                 - 8");
			System.out.println("");
			
			System.out.println("Selecione uma op��o 1, 2, 3, 4, 5, 6, 7, 8 ou 0 para sair");
			System.out.println("Digite: ");
			opcao = s.nextInt();

			String date;
			switch (opcao) {
			case 1:
				dao.readProductList();
				System.out.println("Transa��o Finalizada");
				break;
			case 2:
				dao.readContractsList();
				System.out.println("Transa��o Finalizada");
				break;
			case 3:
				dao.returnAllFinancialLaunch();
				System.out.println("Transa��o Finalizada");
				break;
			case 4:
				System.out.println("Contratos dispon�veis: 1..10:");
				key = s.nextInt();
				dao.returnSpecifiedFinancialLaunchByContract(key);
				System.out.println("Transa��o Finalizada");
				break;
			case 5:
				System.out.println("Contratos dispon�veis: 1..10:");
				key = s.nextInt();
				System.out.println("Periodo fixado em 2020-08-31 (Efeito did�tico)");
				date = input.nextLine();
				dao.returnSpecifiedFinancialLaunchByContractAndDate(key, date);
				System.out.println("Transa��o Finalizada");
				break;
			case 6:
				System.out.println("Sugest�o em 2020-08-22 a 2020-09-03 (Efeito did�tico)");
				initDate = input.nextLine();
				System.out.println("Sugest�o em 2020-08-22 a 2020-09-03 (Efeito did�tico)");
				finalDate = input.nextLine();
				dao.returnFinancialLaunchByDateInterval(initDate = "2020/08/01", finalDate = "2020/09/31");
				System.out.println("Transa��o Finalizada");
				break;
			case 7:
				System.out.println("Sugest�o em 2020-08-22 a 2020-09-03 (Efeito did�tico)");
				initDate = input.nextLine();
				System.out.println("Sugest�o em 2020-08-22 a 2020-09-03 (Efeito did�tico)");
				finalDate = input.nextLine();
				dao.returnFinancialLaunchByDateIntervalSqlSum(initDate, finalDate);
				System.out.println("Transa��o Finalizada");
				break;
			case 8:
				dao.returnAllFinancialLaunchCreated();
				System.out.println("Transa��o Finalizada");
				break;	
			case 0:
				dao.closeConnection();
				System.out.println("o banco foi fechado e sistema finalizado");
				break;

			}
			System.out.println("");
		}
	}
}