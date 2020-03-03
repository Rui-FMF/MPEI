function ordem_retiradas = SimUrna(bolas_retiradas,total_bolas)
    prob = ones(1,total_bolas)*(1/total_bolas);
    ordem_retiradas = zeros(1,bolas_retiradas);
    count=1;
    if bolas_retiradas > total_bolas
        disp('as bolas a retirar têm de ser menos do que as bolas totais');
    else
        while count <= bolas_retiradas
            a=rand();
            i=1+sum(a>cumsum(prob));
            if any(ordem_retiradas(:)==i)
                
            else
                ordem_retiradas(count)=i; 
                count=count+1;
            end
        end
    end
end

