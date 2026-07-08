if has('persistent_undo')
    let s:undo_dir = expand('~/.vim/undo//')

    if !isdirectory(s:undo_dir)
        call mkdir(s:undo_dir, 'p', 0700)
    endif

    let &undodir = s:undo_dir
    set undofile
endif

set writebackup
set nobackup

set bs=2
set number
set nocompatible
set hidden
set expandtab
set shiftwidth=2
set tabstop=2
filetype indent on
syntax enable

set nrformats=hex
set incsearch



