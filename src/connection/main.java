package connection;

import java.util.Scanner;

public class main {
	public static void main(String[] args) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		Scanner s = new Scanner(System.in);

		System.out.println("Estado da conexão");
		dao.readDataBase();
		System.out.println("");

		System.out.println("Lista de ações:");
		System.out.println("Listar Produtos                                               - 1");
		System.out.println("Listar Contratos                                              - 2");
		System.out.println("Criar Lançamento Financeiro                                   - 3");
		System.out.println("Criar Lançamento Financeiro Específico por Contrato           - 4");
		System.out.println("Criar Lançamento Financeiro Específico por Contrato e Período - 5");
		System.out.println("Criar Lançamento Financeiro Específico por Período            - 6");
		System.out.println("Criar Lançamento Financeiro Específico por Período Somatório  - 7");
		System.out.println("");
		
		int opcao = 1;
		int key;

		while (opcao != 0) {
			System.out.println("Selecione uma opção 1, 2, 3, 4, 5, 6, 7 ou 0 para sair");
			System.out.println("Digite: ");
			opcao = s.nextInt();

			switch (opcao) {
			case 1:
				dao.readProductList();
				break;
			case 2:
				dao.readContractsList();
				break;
			case 3:
				dao.createAllFinancialLaunch();
				break;
			case 4:
				System.out.println("Contratos disponíveis: 1..10:");
				key = s.nextInt();
				dao.createAllFinancialLaunchSpecifiedContract(key);
				break;
			case 5:
				System.out.println("Contratos disponíveis: 1..10:");
				key = s.nextInt();
				System.out.println("Periodo fixado em 31/08/2020 (Efeito didático)");
				String date = "2020-08-31";
				dao.createAllFinancialLaunchSpecifiedContractDate(key, date);
				break;
			case 6:
				System.out.println("Periodo fixado entre 22/08/2020 e 03/09/2020 (Efeito didático)");
				String initDate = "2020/08/01";
				String finalDate = "2020/09/31";
				dao.createAllFinancialLaunchSpecifiedDate(initDate, finalDate);
				break;
			case 7:
				System.out.println("Periodo fixado entre 22/08/2020 e 03/09/2020 (Efeito didático) - Lançamento gerado no Banco de Dados");
				String initDateSum1 = "2020/08/01";
				String finalDateSum1 = "2020/09/31";
				dao.createAllFinancialLaunchSpecifiedDateSum(initDateSum1, finalDateSum1);
				break;
			case 0:
				System.out.println("o banco foi fechado e sistema finalizado");
				break;

			}
			System.out.println("");
		}
	}
}